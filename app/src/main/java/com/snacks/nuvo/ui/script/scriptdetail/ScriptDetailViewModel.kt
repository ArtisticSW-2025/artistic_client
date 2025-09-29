package com.snacks.nuvo.ui.script.scriptdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.CallSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScriptDetailViewModel @Inject constructor(
    private val callSessionRepository: CallSessionRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScriptDetailUiState())
    val uiState: StateFlow<ScriptDetailUiState> = _uiState.asStateFlow()

    fun initScreen(
        id: String?,
        isSmallTalkMode: Boolean,
        isEmergencyMode: Boolean,
    ) {
        // 순차 실행을 위해 코루틴 사용
        viewModelScope.launch {
            initParams(id, isSmallTalkMode, isEmergencyMode)
            getScriptDetail()
        }
    }

    fun initParams(
        id: String?,
        isSmallTalkMode: Boolean,
        isEmergencyMode: Boolean,
    ) {
        _uiState.value = _uiState.value.copy(
            id = id,
            isSmallTalkMode = isSmallTalkMode,
            isEmergencyMode = isEmergencyMode
        )
    }

    suspend fun getScriptDetail() {
        if (_uiState.value.id == null) return

        // 로딩 노출
        _uiState.value = _uiState.value.copy(isLoading = true)

        // 데이터 로드
        val scriptInfo = callSessionRepository.getScriptDetail(_uiState.value.id!!)
        _uiState.value = _uiState.value.copy(
            title = scriptInfo.name,
            mission = scriptInfo.mission,
            goal = scriptInfo.purpose,
            dialogues = listOf(
                Dialogue(
                    speaker = "병원 직원",
                    content = scriptInfo.script
                ),
            )
        )

        // 로딩 숨기기
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    fun startScript(id: String) {}
}