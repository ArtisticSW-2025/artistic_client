package com.snacks.nuvo.ui.ranking

data class RankingUiState(
    val isLoading: Boolean = false,
    val rankings: List<RankingItem> = emptyList(),
) {
    val topThreeRankings: List<RankingItem>
        get() = rankings.take(3)

    val remainingRankings: List<RankingItem>
        get() = rankings.drop(3)

}

data class RankingItem(
    val rank: Int,
    val name: String,
    val score: Int,
    val profileImageUrl: String? = null
)
