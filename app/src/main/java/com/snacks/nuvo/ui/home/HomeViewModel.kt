package com.snacks.nuvo.ui.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getUserName()
        getRank()
        getScore()
        getTodayMission()
        getRecommendScripts()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getUserName() {
        _uiState.value = _uiState.value.copy(userName = "DODO")
    }

    private fun getRank() {
        _uiState.value = _uiState.value.copy(rank = 128)
    }

    private fun getScore() {
        _uiState.value = _uiState.value.copy(score = 13847)
    }

    private fun getTodayMission() {
        _uiState.value = _uiState.value.copy(todayMission = "오늘 하루를 요약해서 말해보자")
    }

    private fun getRecommendScripts() {
        _uiState.value = _uiState.value.copy(
            recommendScripts = listOf<RecommendScript>(
                RecommendScript(id = 1, title = "택배 배송 문의", description = "배송 지연이나 변경이 필요할 때"),
                RecommendScript(id = 2, title = "회의 일정 조율", description = "상사나 동료에게 일정 확인이 필요할 때"),
                RecommendScript(id = 3, title = "병원 진료 예약", description = "전화로 병원 예약할 때"),
            )
        )
    }
}
