package com.snacks.nuvo.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

fun NavGraphBuilder.profileGraph(
    appState: NuvoAppState
) {
    composable(
        route = Routes.Profile.ROUTE
    ) {
        ProfileScreen(appState)
    }
}