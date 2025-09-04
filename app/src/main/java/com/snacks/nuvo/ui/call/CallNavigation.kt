package com.snacks.nuvo.ui.call

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.call.calling.CallingScreen
import com.snacks.nuvo.ui.call.on_call.OnCallScreen
import com.snacks.nuvo.ui.call.result.CallResultScreen

fun NavGraphBuilder.callGraph(appState: NuvoAppState) {
    navigation(
        route = "${Routes.Call.ROUTE}?" +
                "prevName={prevName}&" +
                "contactName={contactName}&" +
                "callStatus={callStatus}&" +
                "isReceived={isReceived}&" +
                "isTodayMission={isTodayMission}&" +
                "todayMission={todayMission}&" +
                "todayMissionDateString={todayMissionDateString}",
        arguments = listOf(
            navArgument("prevName") { defaultValue = "병원 초진 예약 전화" },
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
        startDestination = Routes.Call.CALL_ENTRY
    ) {
        composable(Routes.Call.CALL_ENTRY) { backStackEntry ->
            val isConnected = backStackEntry.arguments?.getBoolean("isTodayMission") ?: false

            LaunchedEffect(Unit) {
                val destination = if (isConnected) {
                    Routes.Call.ON_CALL
                } else {
                    Routes.Call.CALLING
                }

                appState.navController.navigate(destination) {
                    popUpTo(Routes.Call.CALL_ENTRY) { inclusive = true }
                }
            }
        }
        composable(Routes.Call.CALLING) {
            val callViewModel: CallViewModel = hiltViewModel(
                remember(it) { appState.navController.getBackStackEntry(Routes.Call.ROUTE) }
            )
            CallingScreen(
                onNavigateBack = { appState.navController.popBackStack() },
                viewModel = callViewModel,
                onConnected = {
                    callViewModel.setIsReceived(true)
                    appState.navController.navigate(Routes.Call.ON_CALL) {
                        popUpTo(Routes.Call.CALLING) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Routes.Call.ON_CALL) {
            val callViewModel: CallViewModel = hiltViewModel(
                remember(it) { appState.navController.getBackStackEntry(Routes.Call.ROUTE) }
            )
            OnCallScreen(
                viewModel = callViewModel,
                onNavigateBack = { appState.navController.popBackStack() },
                onCallEnded = { appState.navController.navigate(Routes.Call.RESULT) {
                    popUpTo(Routes.Call.ON_CALL) {
                        inclusive = true
                    }
                } }
            )
        }
        composable(Routes.Call.RESULT) {
            val callViewModel: CallViewModel = hiltViewModel(
                remember(it) { appState.navController.getBackStackEntry(Routes.Call.ROUTE) }
            )
            CallResultScreen(
                viewModel = callViewModel,
                onNavigateBack = { appState.navController.popBackStack() },
                onRetry = {
                    callViewModel.resetCall()
                    appState.navController.navigate(Routes.Call.CALLING){
                        popUpTo(Routes.Call.RESULT) {
                            inclusive = true
                        }
                    }
                },
                onNextScript = { /* 다른 스크립트 선택 화면으로 이동 */ }
            )
        }
    }
}