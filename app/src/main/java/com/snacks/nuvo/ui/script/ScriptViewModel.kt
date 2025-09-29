package com.snacks.nuvo.ui.script

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TAG
import com.snacks.nuvo.data.repository.CallSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScriptViewModel @Inject constructor(
    private val callSessionRepository: CallSessionRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ScriptUiState())
    val uiState: StateFlow<ScriptUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getScriptItems()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    fun getScriptItems() {
        viewModelScope.launch {
            val scriptItems = callSessionRepository.getScripts()
            _uiState.value = _uiState.value.copy(scriptItems = scriptItems)
        }
    }

    fun onChipClick(index: Int) {
        // 칩 ui 변경
        val updatedSelectedChipIndexes =
            if (index in _uiState.value.selectedChipIndexes) _uiState.value.selectedChipIndexes - index
            else _uiState.value.selectedChipIndexes + index
        _uiState.value = _uiState.value.copy(selectedChipIndexes = updatedSelectedChipIndexes)
        Log.d(TAG, "선택된 카테고리 인덱스: $updatedSelectedChipIndexes")

        // 스크립트 필터링 적용
        var searchCategory: List<String>? = null
        // 선택된 카테고리가 있다면, 검색 api 호출을 위해 List<Int>를 List<String>으로 변환
        if (!updatedSelectedChipIndexes.isEmpty()) {
            searchCategory = updatedSelectedChipIndexes.map { index ->
                _uiState.value.chipLabels[index]
            }
        }
        Log.d(TAG, "검색 카테고리: $searchCategory")
        viewModelScope.launch {
            val scriptItems = callSessionRepository.getScripts(category = searchCategory)
            _uiState.value = _uiState.value.copy(scriptItems = scriptItems)
        }
    }

    fun toggleSmallTalkMode() {
        _uiState.value = _uiState.value.copy(isSmallTalkMode = !_uiState.value.isSmallTalkMode)
    }

    fun toggleEmergencyMode() {
        _uiState.value = _uiState.value.copy(isEmergencyMode = !_uiState.value.isEmergencyMode)
    }
}