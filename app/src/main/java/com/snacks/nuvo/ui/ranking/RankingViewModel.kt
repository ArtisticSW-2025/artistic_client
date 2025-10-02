package com.snacks.nuvo.ui.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TAG
import com.snacks.nuvo.data.repository.RankingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val rankingRepository: RankingRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RankingUiState())
    val uiState: StateFlow<RankingUiState> = _uiState.asStateFlow()

    init {
        getRankingItems()
    }

    fun getRankingItems() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val rankingItems = rankingRepository.getRanking().map {
                    RankingItem(
                        rank = it.rank,
                        name = it.username,   // API에서 username 사용
                        score = it.points     // API에서 points 사용
                    )
                }
                _uiState.value = _uiState.value.copy(rankings = rankingItems)
                Log.d(TAG, "랭킹 아이템 불러오기 성공: $rankingItems")
            } catch (e: Exception) {
                Log.e(TAG, "랭킹 불러오기 실패", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}
