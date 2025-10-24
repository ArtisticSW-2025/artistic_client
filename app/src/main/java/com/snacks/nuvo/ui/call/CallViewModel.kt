package com.snacks.nuvo.ui.call

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.UserRepository
import com.snacks.nuvo.network.model.request.UserMissionRequest
import com.snacks.nuvo.network.model.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class CallViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context,
    private val savedStateHandle: SavedStateHandle,
    private val userRepository: UserRepository,
) : ViewModel() {
    val callSessionId: StateFlow<String> = savedStateHandle.getStateFlow("callSessionId", "123e4567-e89b-12d3-a456-426614174000")
    val prevName: StateFlow<String> = savedStateHandle.getStateFlow("prevName", "병원 초진 예약 전화")
    val contactName: StateFlow<String> = savedStateHandle.getStateFlow("contactName", "힐링 병원")
    val callStatus: StateFlow<CallStatus> =
        savedStateHandle.getStateFlow("callStatus", CallStatus.OUTGOING.name)
            .map { statusString ->
                runCatching { CallStatus.valueOf(statusString) }
                    .getOrDefault(CallStatus.OUTGOING)
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CallStatus.OUTGOING)
    val isReceived: StateFlow<Boolean> = savedStateHandle.getStateFlow("isReceived", false)
    val isTodayMission: StateFlow<Boolean> = savedStateHandle.getStateFlow("isTodayMission", false)
    val todayMission: StateFlow<String> = savedStateHandle.getStateFlow("todayMission", "")
    val todayMissionDateString: StateFlow<String> = savedStateHandle.getStateFlow("todayMissionDateString", "")

    private val _uiState = MutableStateFlow(CallUiState())
    val uiState: StateFlow<CallUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    private var callService: CallService? = null
    private var isServiceBound = false
    private var serviceStateJob: Job? = null

    private var getUserInfoJob: Job? = null

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as CallService.CallBinder
            callService = binder.getService()
            isServiceBound = true

            viewModelScope.launch {
                getUserInfoJob?.join()

                val currentUiState = _uiState.value
                if (currentUiState.userId.isNotBlank() && currentUiState.callSessionId.isNotBlank()) {
                    callService?.connectWebSocket(currentUiState.userId, currentUiState.callSessionId)
                }

                _uiState.value = _uiState.value.copy(isLoading = false)
            }

            observeServiceState()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isServiceBound = false
            serviceStateJob?.cancel()
        }
    }

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getUserInfoJob = getUserInfo()

        setCallSessionId(callSessionId.value)
        setPrevName(prevName.value)

        setCallStatus(callStatus.value)
        setIsReceived(isReceived.value)
        setContactName(contactName.value)

        setIsTodayMission(isTodayMission.value)
        setTodayMission(todayMission.value)
        setTodayMissionDateString(todayMissionDateString.value)

        getResult()
    }

    private fun getUserInfo(): Job {
        return viewModelScope.launch {
            val userInfo = userRepository.getUserInfo()
            _uiState.value = _uiState.value.copy(
                userId = userInfo.id!!,
            )
        }
    }

    fun startAndBindService(context: Context) {
        if (isServiceBound) return
        Intent(context, CallService::class.java).also { intent ->
            context.startService(intent)
            context.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun unbindService(context: Context) {
        if (isServiceBound) {
            context.unbindService(serviceConnection)
            isServiceBound = false
        }
    }

    fun startListening(context: Context) {
        if (!isServiceBound) {
            return
        }

        callService?.startListening(context)
    }

    fun stopListening(isEnd: Boolean = false) {
        callService?.stopListening(isEnd)
    }

    fun startTimer() {
        if (timerJob?.isActive == true) return
        timerJob = viewModelScope.launch {
            while (isActive) {
                delay(1000L)
                _uiState.value = _uiState.value.copy(elapsedTime = _uiState.value.elapsedTime + 1)
            }
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }

    fun setCallSessionId(callSessionId: String) {
        _uiState.value = _uiState.value.copy(callSessionId = callSessionId)
    }

    fun setPrevName(prevName: String) {
        _uiState.value = _uiState.value.copy(prevName = prevName)
    }

    fun setCallStatus(callStatus: CallStatus?) {
        _uiState.value = _uiState.value.copy(callStatus = callStatus)
    }

    fun setIsReceived(isReceived: Boolean) {
        _uiState.value = _uiState.value.copy(isReceived = isReceived)
    }

    private fun setContactName(contactName: String) {
        _uiState.value = _uiState.value.copy(contactName = contactName)
    }

    fun setIsEndPossible(isEndPossible: Boolean) {
        _uiState.value = _uiState.value.copy(isEndPossible = isEndPossible)
    }

    fun setIsTodayMission(isTodayMission: Boolean) {
        _uiState.value = _uiState.value.copy(isTodayMission = isTodayMission)
    }

    fun setTodayMission(todayMission: String) {
        _uiState.value = _uiState.value.copy(todayMission = todayMission)
    }

    fun setTodayMissionDateString(todayMissionDateString: String) {
        _uiState.value = _uiState.value.copy(todayMissionDateString = todayMissionDateString)
    }

    fun setIsTodayMissionFinish(isTodayMissionFinish: Boolean) {
        _uiState.value = _uiState.value.copy(isTodayMissionFinish = isTodayMissionFinish)
    }

    fun setIsDetailedResult(isDetailedResult: Boolean) {
        _uiState.value = _uiState.value.copy(isDetailedResult = isDetailedResult)
    }

    private fun getResult() {
        _uiState.value = _uiState.value.copy(result = "말하기가 점점 더 좋아지고 있어요!")
    }

    private fun getScore() {
        _uiState.value = _uiState.value.copy(score = 98)
    }

    private fun getFeedback() {
        _uiState.value = _uiState.value.copy(
            feedbackContents = listOf<String>(
                "예의: 일방적으로 전화하고, 질문에 단답으로 답변하며, 마무리 감사 인사을 덧붙어 기본적인 예의는 지켰습니다.",
                "능동성: 직원이 모든 정보를 하나씩 물어봐야 하는 등 다소 수동적인 소통 방식이 보였습니다. 본인 여부를 먼저 밝히는 등 소극적인 태도가 돋보였습니다.",
                "정보의 정확성: 직원의 확인 질문에 따라 정보를 정확히 제공하여 결국 예약 변경이 가능하도록 했습니다 "
            )
        )
    }

    fun endCallAndGetFeedback() {
        _uiState.value = _uiState.value.copy(isLoading = true)

        stopTimer()
        stopListening(true)
        callService?.requestFeedback()
    }

    private fun addMissionResult(score: Int, feedback: List<String>) {
        viewModelScope.launch {
            val feedbackString = feedback.joinToString("\n")

            userRepository.addMissionResult(
                userMissionRequest = UserMissionRequest(
                    callDuration = _uiState.value.elapsedTime,
                    score = score,
                    feedback = feedbackString
                )
            )
        }
    }

    fun resetCall() {
        stopTimer()
        _uiState.value = _uiState.value.copy(
            elapsedTime = 0,
            isRecording = false,
            isEndPossible = false,
            callScripts = emptyList(),
        )
    }

    override fun onCleared() {
        super.onCleared()
        stopListening()
        stopTimer()
        if (isServiceBound) {
            callService?.disconnectWebSocket()
            applicationContext.unbindService(serviceConnection)
            isServiceBound = false
        }
        Intent(applicationContext, CallService::class.java).also { intent ->
            applicationContext.stopService(intent)
        }
    }

    private fun observeServiceState() {
        serviceStateJob = viewModelScope.launch {
            callService?.uiState?.collect { serviceState ->
                if (serviceState.isFeedbackFailed && !_uiState.value.isFeedbackFailed) {
                    getScore()      // 더미 점수 생성
                    getFeedback()   // 더미 피드백 생성
                } else if (serviceState.score > 0 && serviceState.score != _uiState.value.score) {
                    _uiState.value = _uiState.value.copy(
                        score = serviceState.score,
                        feedbackContents = serviceState.feedbackContents,
                        sentenceFeedbacks = serviceState.sentenceFeedbacks
                    )

                    addMissionResult(serviceState.score, serviceState.feedbackContents)
                }

                _uiState.value = _uiState.value.copy(
                    isLoading = serviceState.isLoading,
                    isRecording = serviceState.isRecording,
                    callScripts = serviceState.callScripts,
                    waveformLevels = serviceState.waveformLevels,
                )
            }
        }
    }
}

class FakeUserRepository : UserRepository {
    override suspend fun getUserInfo(): UserResponse {
        return UserResponse(
            username = "홍길동",
            points = 0,
            createdAt = "",
            updatedAt = ""
        )
    }

    override suspend fun addMissionResult(userMissionRequest: UserMissionRequest): UserResponse {
        TODO("Not yet implemented")
    }
}