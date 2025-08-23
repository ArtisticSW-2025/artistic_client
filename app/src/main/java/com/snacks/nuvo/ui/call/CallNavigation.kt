package com.snacks.nuvo.ui.call

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.call.calling.CallingScreen
import com.snacks.nuvo.ui.call.on_call.OnCallScreen

fun NavGraphBuilder.callGraph(appState: NuvoAppState) {
    navigation(startDestination = Routes.Call.CALLING, route = Routes.Call.ROUTE) {
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
//            val callViewModel: CallViewModel = hiltViewModel(
//                remember(it) { appState.navController.getBackStackEntry(Routes.Call.ROUTE) }
//            )
//            CallResultScreen(
//                viewModel = callViewModel,
//                onNavigateBack = { appState.navController.popBackStack() },
//                onRetry = {
//                    // 다시 연습하면 CallingScreen으로 돌아감
//                    navController.navigate(Routes.Call.CALLING)
//                },
//                onNextScript = { /* 다른 스크립트 선택 화면으로 이동 */ }
//            )
        }
    }
}