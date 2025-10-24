package com.snacks.nuvo.ui.call

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.call.retry.RetryScreen
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.callGraph(appState: NuvoAppState) {
    composable(
        route = "${Routes.Call.ROUTE}?" +
                "prevName={prevName}&" +
                "callSessionId={callSessionId}&" +
                "contactName={contactName}&" +
                "callStatus={callStatus}&" +
                "isReceived={isReceived}&" +
                "isTodayMission={isTodayMission}&" +
                "todayMission={todayMission}&" +
                "todayMissionDateString={todayMissionDateString}",
        arguments = listOf(
            navArgument("prevName") { defaultValue = "병원 초진 예약 전화" },
            navArgument("callSessionId") { defaultValue = "123e4567-e89b-12d3-a456-426614174000" },
            navArgument("contactName") { defaultValue = "힐링 병원" },
            navArgument("callStatus")
            {
                type = NavType.StringType
                defaultValue = CallStatus.OUTGOING.name
            },
            navArgument("isReceived") { defaultValue = false },
            navArgument("isTodayMission") { defaultValue = false },
            navArgument("todayMission") { defaultValue = "" },
            navArgument("todayMissionDateString") { defaultValue = "" },
        ),
    ) { backStackEntry ->
        CallNavHost(
            appState = appState,
            viewModel = hiltViewModel(backStackEntry),
            onRetry = {
                val queryString = backStackEntry.arguments?.let { bundle ->
                    bundle.keySet().joinToString("&") { key ->
                        "$key=${bundle.get(key)}"
                    }
                } ?: ""

                val fullRouteWithArgs = "${Routes.Call.RETRY}?$queryString"

                appState.navController.navigate(fullRouteWithArgs) {
                    popUpTo(backStackEntry.destination.id) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.callRetryScreen(appState: NuvoAppState) {
    composable(
        route = "${Routes.Call.RETRY}?{args}",
        arguments = listOf(navArgument("args") { type = NavType.StringType })
    ) { backStackEntry ->
        val args = backStackEntry.arguments?.getString("args") ?: ""

        RetryScreen(
            appState = appState,
            args = args,
            prevDestinationId = backStackEntry.destination.id
        )
    }
}