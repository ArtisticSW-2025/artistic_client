package com.snacks.nuvo.ui.ranking

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TAG
import com.snacks.nuvo.data.repository.RankingRepository
import com.snacks.nuvo.network.model.response.RankingResponse
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
                        name = it.username,
                        score = it.points
                    )
                }
                _uiState.value = _uiState.value.copy(rankings = rankingItems)
                Log.d(TAG, "랭킹 아이템 불러오기 성공: $rankingItems")

                addRankingDummyData()
            } catch (e: Exception) {
                Log.e(TAG, "랭킹 불러오기 실패", e)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }

    fun addRankingDummyData()
    {
        val originRankings = _uiState.value.rankings

        val updatedOriginRankings = originRankings.map { item ->
            item.copy(rank = item.rank + 3)
        }

        val dummyData = listOf(
            RankingItem(rank = 1, name = "이정빈", score = 123456),
            RankingItem(rank = 2, name = "송예진", score = 123455),
            RankingItem(rank = 3, name = "신소미", score = 12345)
        )

        val newRankings = dummyData + updatedOriginRankings

        _uiState.value = _uiState.value.copy(rankings = newRankings)
    }
}

class FakeRankingRepository : RankingRepository {
    override suspend fun getRanking(): List<RankingResponse> {
        return listOf(
            RankingResponse(
                id = "",
                rank = 4,
                username = "4등",
                points = 123,
                email = "",
                missionCount = 1234,
                missionRecordCount = 123,
                missionTotalSeconds = 123456,
                totalCallDuration = 123456
            ),
            RankingResponse(
                id = "",
                rank = 5,
                username = "5등",
                points = 11111,
                email = "",
                missionCount = 1234,
                missionRecordCount = 123,
                missionTotalSeconds = 123456,
                totalCallDuration = 123456
            )
        )
    }

}