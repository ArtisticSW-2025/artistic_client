package com.snacks.nuvo.ui.call

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

fun NavGraphBuilder.callGraph(appState: NuvoAppState) {
    composable(
        route = Routes.Call.ROUTE
    ) {
        CallScreen(appState)
    }
}