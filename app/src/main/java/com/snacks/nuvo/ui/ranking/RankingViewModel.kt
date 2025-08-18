package com.snacks.nuvo.ui.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RankingUiState())
    val uiState: StateFlow<RankingUiState> = _uiState.asStateFlow()

    init {
        loadRankings()
    }

    fun loadRankings() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true,)

            try {
                delay(1000) // 네트워크 지연 시뮬레이션
                val rankings = fetchRankingsData()

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    rankings = rankings,
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                )
            }
        }
    }
    private suspend fun fetchRankingsData(): List<RankingItem> {
        return listOf(
            RankingItem(1, "임유정꼬봉이정빈", 1200),
            RankingItem(2, "송예진", 1100),
            RankingItem(3, "신소미", 1000),
            RankingItem(4, "이이이이이성원", 9100),
            RankingItem(4, "이성원원원원원원원원원원원원원원원원", 900),
            RankingItem(4, "이성원", 90000000),
            RankingItem(4, "이성원", 900),
            RankingItem(4, "이성원", 900),
            RankingItem(4, "이성원", 900),
            RankingItem(4, "이성원", 900),
            RankingItem(4, "이성원", 900),
        )
    }
}