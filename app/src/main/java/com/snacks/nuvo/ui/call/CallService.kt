package com.snacks.nuvo.ui.call

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.gson.Gson
import com.snacks.nuvo.R
import com.snacks.nuvo.data.repository.CallRepository
import com.snacks.nuvo.network.model.CallFeedbackResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class CallService : Service(), TextToSpeech.OnInitListener {

    // Hilt를 통해 '데이터 총괄 매니저'인 Repository를 주입받습니다.
    @Inject
    lateinit var callRepository: CallRepository

    // Service와 ViewModel을 연결하기 위한 Binder
    private val binder = CallBinder()
    inner class CallBinder : Binder() {
        fun getService(): CallService = this@CallService
    }
    override fun onBind(intent: Intent): IBinder = binder

    // Service의 생명주기에 맞는 CoroutineScope 정의
    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var listeningJob: Job? = null
    private var webSocketJob: Job? = null
    private var tts: TextToSpeech? = null

    // 피드백을 요청했는지 상태를 관리하는 플래그
    private var isFeedbackRequested = false

    // Service가 관리하는 UI 상태. ViewModel은 이 StateFlow를 구독하여 UI에 데이터를 전달합니다.
    private val _uiState = MutableStateFlow(CallUiState())
    val uiState = _uiState.asStateFlow()

    // --- Service 생명주기 관련 함수 ---
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        tts = TextToSpeech(this, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // 언어를 한국어로 설정
            val result = tts?.setLanguage(Locale.KOREAN)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "한국어를 지원하지 않습니다.")
            } else {
                Log.d("TTS", "TTS 엔진 초기화 성공")
            }
        } else {
            Log.e("TTS", "TTS 엔진 초기화 실패")
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification = createNotification()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(
                NOTIFICATION_ID,
                notification,
                ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE
            )
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        listeningJob?.cancel()
        webSocketJob?.cancel()

        serviceScope.launch {
            callRepository.stopListening()
            callRepository.disconnectWebSocket()
        }

        tts?.stop()
        tts?.shutdown()
    }

    private fun speakOut(text: String) {
        // QUEUE_ADD: 현재 재생 중인 음성이 끝나면 이어서 재생
        // QUEUE_FLUSH: 현재 재생 중인 음성을 중단하고 즉시 재생
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    // --- ViewModel이 호출할 공개 함수들 ---

    /** WebSocket 연결을 시작하고 서버 메시지를 수신 대기합니다. */
    fun connectWebSocket(user: String, sessionId: String) {
        if (webSocketJob?.isActive == true) return
        webSocketJob = serviceScope.launch {
            // Repository가 반환하는 Flow를 구독하여 비동기 데이터를 처리합니다.
            callRepository.connectWebSocket(user, sessionId).collect { message ->
                if (isFeedbackRequested) {
                    parseFeedbackResponse(message)
                } else {
                    addAiScript(message)
                    speakOut(message)
                }
            }
        }
    }

    /** STT 음성 인식을 시작합니다. */
    fun startListening(context: Context) {
        if (listeningJob?.isActive == true) return

        addUserScript()

        listeningJob = serviceScope.launch {
            callRepository.startListening(context).collect { result ->
                updateLastUserScript(result.transcribedText)
                updateWaveform(result.volume)
            }
        }
    }

    fun requestFeedback() {
        if (isFeedbackRequested) return

        isFeedbackRequested = true

//        val feedbackRequestMessage = "{\"action\": \"end_call\"}"
        val feedbackRequestMessage = "지금까지 한 대화를 통해 {\"score\": (98과 같이 100점 만점의 숫자로된 점수), \"feedback\": {[ \"예의:한문장의 피드백 내용\", \"능동성:한문장의 피드백 내용\", \"정보의 정확성:한문장의 피드백 내용\" ](3개의 문자열이 포함된 배열)}} 형태로 응답해줘"

        serviceScope.launch {
            callRepository.sendUserMessage(feedbackRequestMessage)
        }
        Log.d("CallService", "Requested feedback with message: $feedbackRequestMessage")
    }

    /** STT 음성 인식을 중지합니다. */
    fun stopListening() {
        listeningJob?.cancel()

        serviceScope.launch {
            callRepository.stopListening()
        }

        val lastUserScript = _uiState.value.callScripts.lastOrNull { !it.isAI }
        if (lastUserScript != null && lastUserScript.script.isNotBlank()) {
            serviceScope.launch {
                callRepository.sendUserMessage(lastUserScript.script)
            }
        }

        _uiState.value = _uiState.value.copy(isRecording = false)
    }

    /** WebSocket 연결을 종료합니다. */
    fun disconnectWebSocket() {
        webSocketJob?.cancel()
        serviceScope.launch { callRepository.disconnectWebSocket() }
    }

    // --- UI 상태 업데이트를 위한 내부 함수들 ---
    // 이 함수들은 Repository로부터 받은 데이터를 UI가 사용하기 좋은 형태로 가공하는 역할을 합니다.
    private fun addAiScript(text: String) {
        val newAiScript = CallScript(script = text, isAI = true, isLast = true)
        val updatedScripts = _uiState.value.callScripts.map { it.copy(isLast = false) } + newAiScript
        _uiState.value = _uiState.value.copy(callScripts = updatedScripts)
    }

    private fun addUserScript() {
        val newScripts = _uiState.value.callScripts.map { it.copy(isLast = false) } +
                CallScript(script = "", isAI = false, isLast = true)
        _uiState.value = _uiState.value.copy(callScripts = newScripts, isRecording = true)
    }

    private fun updateLastUserScript(text: String) {
        val currentScripts = _uiState.value.callScripts
        val lastUserScriptIndex = currentScripts.indexOfLast { !it.isAI }

        if (lastUserScriptIndex != -1) {
            val updatedScripts = currentScripts.toMutableList()
            val originalScript = updatedScripts[lastUserScriptIndex]
            updatedScripts[lastUserScriptIndex] = originalScript.copy(script = text)
            _uiState.value = _uiState.value.copy(callScripts = updatedScripts)
        }
    }

    private fun parseFeedbackResponse(jsonResponse: String) {
        try {
            val feedbackResponse = Gson().fromJson(jsonResponse, CallFeedbackResult::class.java)
            _uiState.value = _uiState.value.copy(
                score = feedbackResponse.score,
                feedbackContents = feedbackResponse.feedback,
                isLoading = false,
                isFeedbackFailed = false
            )
            isFeedbackRequested = false
            Log.d("CallService", "Feedback response parsed successfully.")
        } catch (e: Exception) {
            Log.e("CallService", "Failed to parse feedback response: $jsonResponse", e)
            _uiState.value = _uiState.value.copy(isLoading = false, isFeedbackFailed = true)
        }
    }

    private fun updateWaveform(volume: Float) {
        val currentLevels = _uiState.value.waveformLevels
        val newLevels = (currentLevels.drop(1) + volume).takeLast(_uiState.value.waveformLineCount)
        _uiState.value = _uiState.value.copy(waveformLevels = newLevels)
    }

    // --- Foreground Service 알림 관련 ---
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, "통화 서비스", NotificationManager.IMPORTANCE_LOW)
        getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("통화 기능 사용 중")
            .setContentText("진행 중인 통화가 있습니다.")
            .setSmallIcon(R.drawable.ic_call_filled)
            .build()
    }

    companion object {
        const val CHANNEL_ID = "CallServiceChannel"
        const val NOTIFICATION_ID = 1
    }
}