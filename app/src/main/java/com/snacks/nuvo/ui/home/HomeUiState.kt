package com.snacks.nuvo.ui.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val rank: Int? = null,
    val score: Int? = null,
    val todayMission: String = "",
    val recommendScripts: List<RecommendScript> = emptyList<RecommendScript>(),
    val todayMissionId: Int? = null,
)

data class RecommendScript(
    val id: String?,
    val title: String,
    val description: String,
)