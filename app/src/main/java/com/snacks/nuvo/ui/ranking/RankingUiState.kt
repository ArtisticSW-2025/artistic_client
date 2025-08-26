package com.snacks.nuvo.ui.ranking

data class RankingUiState(
    val isLoading: Boolean = false,
    val rankings: List<RankingItem> = listOf(
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
