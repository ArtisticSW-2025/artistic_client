package com.snacks.nuvo.ui.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.Routes

fun NavGraphBuilder.homeGraph() {
    composable(
        route = Routes.Home.ROUTE
    ) {
        HomeScreen()
    }
}