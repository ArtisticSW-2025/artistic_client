package com.snacks.nuvo.ui.call

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CallViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CallUiState())
    val uiState: StateFlow<CallUiState> = _uiState.asStateFlow()

    private var waveformJob: Job? = null
    private var timerJob: Job? = null

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getPrevName()
        getCallStatus()
        getContactName()
        getCallScripts()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getPrevName() {
        _uiState.value = _uiState.value.copy(prevName = "병원 초진 예약 전화")
    }

    fun setPrevName(prevName: String) {
        _uiState.value = _uiState.value.copy(prevName = prevName)
    }

    private fun getCallStatus() {
        _uiState.value = _uiState.value.copy(callStatus = CallStatus.OUTGOING)
    }

    fun setCallStatus(callStatus: CallStatus?) {
        _uiState.value = _uiState.value.copy(callStatus = callStatus)
    }

    fun setIsReceived(isReceived: Boolean) {
        _uiState.value = _uiState.value.copy(isReceived = isReceived)
    }

    private fun getContactName() {
        _uiState.value = _uiState.value.copy(contactName = "힐링 병원")
    }

    fun startRecording() {
        waveformJob?.cancel()
        waveformJob = viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRecording = true)
            while (isActive) {
                // 실제 앱에서는 여기서 마이크 입력 값을 받아옵니다.
                // 지금은 랜덤 데이터로 시뮬레이션합니다.
                val newLevels = List(_uiState.value.waveformLineCount) { Random.nextFloat() }
                _uiState.value = _uiState.value.copy(waveformLevels = newLevels)

                delay(100)
            }
        }
    }

    fun stopRecording() {
        waveformJob?.cancel()
        _uiState.value = _uiState.value.copy(isRecording = false)
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

    fun setTodayMissionDate(todayMissionDate: LocalDate) {
        _uiState.value = _uiState.value.copy(todayMissionDate = todayMissionDate)
    }

    fun setIsTodayMissionFinish() {
        _uiState.value = _uiState.value.copy(isTodayMissionFinish = true)
    }

    private fun getCallScripts() {
        _uiState.value = _uiState.value.copy(
            callScripts = listOf<CallScript>(
                CallScript(script = "안녕하세요, 힐링내과입니다.\n무엇을 도와드릴까요?", isAI = true),
                CallScript(script = "안녕하세요.\n초진 예약을 하고 싶어서 전화드렸어요.", isAI = false),
                CallScript(script = "네, 증상은 어떤 것이신가요?", isAI = true, isLast = true),
            )
        )
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

    override fun onCleared() {
        super.onCleared()
        waveformJob?.cancel()
        stopTimer()
    }
}