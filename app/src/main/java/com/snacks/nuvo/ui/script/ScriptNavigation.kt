package com.snacks.nuvo.ui.script

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.script.scriptdetail.ScriptDetailScreen
import com.snacks.nuvo.ui.script.scriptdetail.ScriptDetailViewModel

fun NavGraphBuilder.scriptGraph(
    appState: NuvoAppState,
) {
    composable(
        route = Routes.Script.ROUTE
    ) {
        val viewModel: ScriptViewModel = hiltViewModel()

        ScriptScreen(
            appState = appState,
            viewModel = viewModel
        )
    }

    composable(
        route = "${Routes.Script.SCRIPT_DETAIL}/{id}?isSmallTalkMode={isSmallTalkMode}&isEmergencyMode={isEmergencyMode}",
        arguments = listOf(
            navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            },
            navArgument("isSmallTalkMode") {
                type = NavType.BoolType
                defaultValue = false
            },
            navArgument("isEmergencyMode") {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    ) { entry ->
        val id = entry.arguments?.getString("id") ?: "0"
        val isSmallTalkMode = entry.arguments?.getBoolean("isSmallTalkMode") == true
        val isEmergencyMode = entry.arguments?.getBoolean("isEmergencyMode") == true

        val viewModel: ScriptDetailViewModel = hiltViewModel()

        ScriptDetailScreen(
            appState = appState,
            viewModel = viewModel,
            id = id,
            isSmallTalkMode = isSmallTalkMode,
            isEmergencyMode = isEmergencyMode
        )
    }
}