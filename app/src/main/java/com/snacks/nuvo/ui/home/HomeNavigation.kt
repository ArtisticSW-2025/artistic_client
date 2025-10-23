package com.snacks.nuvo.ui.home

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

fun NavGraphBuilder.homeGraph(
    appState: NuvoAppState,
) {
    composable(
        route = Routes.Home.ROUTE
    ) {
        val viewModel: HomeViewModel = hiltViewModel()

        HomeScreen(
            appState = appState,
            viewModel = viewModel
        )
    }
}