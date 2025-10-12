package com.snacks.nuvo.ui.call

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes

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
            viewModel = hiltViewModel(backStackEntry)
        )
    }
}