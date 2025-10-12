package com.snacks.nuvo.data.datasource

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.app.ActivityCompat
import com.google.api.gax.rpc.ApiStreamObserver
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.speech.v1.*
import com.google.protobuf.ByteString
import com.snacks.nuvo.R
import com.snacks.nuvo.di.IoDispatcher
import com.snacks.nuvo.network.model.SpeechResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.sqrt

@Singleton
class CallDataSourceImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CallDataSource {

    // --- 클라이언트 및 레코더 인스턴스 ---
    private var audioRecord: AudioRecord? = null
    private var speechClient: SpeechClient? = null
    private var requestObserver: ApiStreamObserver<StreamingRecognizeRequest>? = null
    private var webSocket: WebSocket? = null
    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build()

    // --- 오디오 설정 ---
    private val sampleRate = 16000
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT
    private val bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat)

    /** STT 시작 및 마이크 입력을 Flow로 변환 */
    override fun startListening(context: Context): Flow<SpeechResult> = callbackFlow {
        // 권한 확인
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            close(Exception("Audio permission not granted"))
            return@callbackFlow
        }

        try {
            audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig, audioFormat, bufferSize)
            if (audioRecord?.state != AudioRecord.STATE_INITIALIZED) {
                throw IllegalStateException("AudioRecord could not be initialized.")
            }
        } catch (e: Exception) {
            // 객체 생성 또는 초기화 실패 시 즉시 Flow 종료
            close(Exception("Failed to initialize AudioRecord. Another app might be using the microphone.", e))
            return@callbackFlow
        }

        // SpeechClient 초기화
        val credentials = GoogleCredentials.fromStream(context.resources.openRawResource(R.raw.credential))
        val speechSettings = SpeechSettings.newBuilder().setCredentialsProvider { credentials }.build()
        speechClient = SpeechClient.create(speechSettings)

        var lastText = ""
        var isFinal = false

        // 서버 응답 처리
        val responseObserver = object : ApiStreamObserver<StreamingRecognizeResponse> {
            override fun onNext(response: StreamingRecognizeResponse) {
                val result = response.resultsList.firstOrNull()
                if (result != null && result.alternativesCount > 0) {
                    lastText = result.alternativesList[0].transcript
                    isFinal = result.isFinal
                }
            }
            override fun onError(t: Throwable) { close(t) }
            override fun onCompleted() { close() }
        }

        // 스트림 생성 및 설정 전송
        requestObserver = speechClient?.streamingRecognizeCallable()?.bidiStreamingCall(responseObserver)
        val recognitionConfig = RecognitionConfig.newBuilder()
            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
            .setLanguageCode("ko-KR")
            .setSampleRateHertz(sampleRate)
            .build()
        val streamingConfig = StreamingRecognitionConfig.newBuilder()
            .setConfig(recognitionConfig)
            .setInterimResults(true).build()
        requestObserver?.onNext(StreamingRecognizeRequest.newBuilder().setStreamingConfig(streamingConfig).build())

        // 오디오 녹음 시작
        audioRecord?.startRecording()

        // 녹음 루프
        val buffer = ByteArray(bufferSize)
        while (!isClosedForSend) {
            val bytesRead = audioRecord?.read(buffer, 0, buffer.size) ?: -1
            if (bytesRead > 0) {
                val shorts = ShortArray(bytesRead / 2)
                ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).asShortBuffer().get(shorts)
                val rms = sqrt(shorts.map { it.toDouble() * it.toDouble() }.average())
                val volume = (rms / 5000).toFloat().coerceIn(0f, 1f)

                // 결과 방출
                trySend(SpeechResult(transcribedText = lastText, volume = volume, isFinal = isFinal))
                isFinal = false // isFinal은 한번만 방출

                // 서버로 오디오 데이터 전송
                requestObserver?.onNext(StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(buffer, 0, bytesRead)).build())
            }
        }

        // Flow가 닫힐 때 리소스 정리
        awaitClose { stopListening() }
    }.flowOn(ioDispatcher)

    /** STT 중지 */
    override fun stopListening() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null

        requestObserver?.onCompleted()
        requestObserver = null

        speechClient?.close()
        speechClient = null
    }

    /** WebSocket 연결 및 메시지 수신을 Flow로 변환 */
    override fun connectWebSocket(user: String, sessionId: String): Flow<String> = callbackFlow {
        val request = Request.Builder().url("ws://websocket-artsw.duckdns.org/ws?user=$user&session_id=$sessionId").build()
        val webSocketListener = object : WebSocketListener() {
            override fun onMessage(webSocket: WebSocket, text: String) {
                trySend(text) // 수신된 메시지를 Flow로 방출
            }
            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                close(t) // 에러 발생 시 Flow 종료
            }
        }
        webSocket = okHttpClient.newWebSocket(request, webSocketListener)

        // Flow가 닫힐 때 리소스 정리
        awaitClose { disconnectWebSocket() }
    }.flowOn(ioDispatcher)

    /** WebSocket 연결 종료 */
    override fun disconnectWebSocket() {
        webSocket?.close(1000, "Normal closure")
        webSocket = null
    }

    /** WebSocket으로 메시지 전송 */
    override fun sendUserMessage(message: String) {
        webSocket?.send(message)
    }
}