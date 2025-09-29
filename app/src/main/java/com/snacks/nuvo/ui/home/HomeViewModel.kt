package com.snacks.nuvo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.CallSessionRepository
import com.snacks.nuvo.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val callSessionRepository: CallSessionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getUserInfo()
        getTodayMission()
        getRecommendScripts()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = userRepository.getUserInfo()
            _uiState.value = _uiState.value.copy(
                userName = userInfo[0].username,
                rank = userInfo[0].rank ?: 0,
                score = userInfo[0].points
            )
        }
    }

    private fun getTodayMission() {
        _uiState.value = _uiState.value.copy(todayMission = "오늘 하루를 요약해서 말해보자")
    }

    private fun getRecommendScripts() {
        viewModelScope.launch {
            val scripts = callSessionRepository.getRandomScripts(count = 5)
            _uiState.value = _uiState.value.copy(
                recommendScripts = scripts
            )
        }
    }
}
