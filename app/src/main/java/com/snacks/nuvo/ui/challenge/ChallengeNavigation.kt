package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.challengeGraph(
    appState: NuvoAppState
) {
    composable(
        route = Routes.Challenge.ROUTE
    ) {
        ChallengeScreen(appState = appState)
    }
}