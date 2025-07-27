package com.snacks.nuvo.ui.ranking

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.Routes

fun NavGraphBuilder.rankingGraph() {
    composable(
        route = Routes.Ranking.ROUTE
    ) {
        RankingScreen()
    }
}