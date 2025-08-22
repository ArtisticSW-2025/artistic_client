package com.snacks.nuvo.ui.script.scriptdetail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScriptDetailViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ScriptDetailUiState())
    val uiState: StateFlow<ScriptDetailUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getScriptDetail()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    fun initParams(
        id: Int,
        isSmallTalkMode: Boolean,
        isEmergencyMode: Boolean,
    ) {
        _uiState.value = _uiState.value.copy(
            id = id,
            isSmallTalkMode = isSmallTalkMode,
            isEmergencyMode = isEmergencyMode
        )
    }

    fun getScriptDetail() {
        _uiState.value = _uiState.value.copy(
            title = "병원 초진 예약 전화",
            mission = "병원 초진 예약 전화를 성공적으로 완료하세요!",
            goal = "• 병원에 인사하고, 초진 예약하고 싶다고 말하기\n• 증상 간단히 설명하기\n• 가능한 날짜/시간 물어보기\n• 예약 확정 및 마무리 인사하기",
            dialogues = listOf(
                Dialogue(
                    speaker = "병원 직원",
                    content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
                ),
                Dialogue(
                    speaker = "나",
                    content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
                ),
                Dialogue(
                    speaker = "병원 직원",
                    content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
                ),
                Dialogue(
                    speaker = "나",
                    content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
                ),
                Dialogue(
                    speaker = "병원 직원",
                    content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
                ),
                Dialogue(
                    speaker = "나",
                    content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
                ),
                Dialogue(
                    speaker = "병원 직원",
                    content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
                ),
                Dialogue(
                    speaker = "나",
                    content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
                ),
                Dialogue(
                    speaker = "병원 직원",
                    content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
                ),
                Dialogue(
                    speaker = "나",
                    content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
                ),
            )
        )
    }

    fun startScript(id: Int) {}
}