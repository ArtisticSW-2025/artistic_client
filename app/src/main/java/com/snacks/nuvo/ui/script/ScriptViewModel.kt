package com.snacks.nuvo.ui.script

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ScriptViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ScriptUiState())
    val uiState: StateFlow<ScriptUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getScriptItems()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    fun getScriptItems() {
        val scriptItems = listOf<ScriptItem>(
            ScriptItem(
                id = 1,
                title = "병원 초진 예약 전화",
                description = "병원에 인사하고, 초진 예약을 할 때"
            ),
            ScriptItem(
                id = 1,
                title = "병원 초진 예약 전화",
                description = "병원에 인사하고, 초진 예약을 할 때"
            ),
            ScriptItem(
                id = 1,
                title = "병원 초진 예약 전화",
                description = "병원에 인사하고, 초진 예약을 할 때"
            ),
        )
        _uiState.value = _uiState.value.copy(scriptItems = scriptItems)
    }

    fun onChipClick(index: Int) {
        val updatedSelectedChipIndexes =
            if (index in _uiState.value.selectedChipIndexes) _uiState.value.selectedChipIndexes - index
            else _uiState.value.selectedChipIndexes + index
        _uiState.value = _uiState.value.copy(selectedChipIndexes = updatedSelectedChipIndexes)
    }

    fun toggleSmallTalkMode() {
        _uiState.value = _uiState.value.copy(isSmallTalkMode = !_uiState.value.isSmallTalkMode)
    }

    fun toggleEmergencyMode() {
        _uiState.value = _uiState.value.copy(isEmergencyMode = !_uiState.value.isEmergencyMode)
    }

    fun onScriptItemClick(id: Int) {

    }
}