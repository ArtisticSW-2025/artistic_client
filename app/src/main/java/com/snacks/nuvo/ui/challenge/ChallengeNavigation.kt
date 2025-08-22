package com.snacks.nuvo.ui.challenge

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

fun NavGraphBuilder.challengeGraph(appstate: NuvoAppState) {
    composable(
        route = Routes.Challenge.ROUTE
    ) {
        ChallengeScreen(appstate)
    }
}