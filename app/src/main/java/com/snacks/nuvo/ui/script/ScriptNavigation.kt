package com.snacks.nuvo.ui.script

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.home.HomeScreen

fun NavGraphBuilder.scriptGraph() {
    composable(
        route = Routes.Script.ROUTE
    ) {
        ScriptScreen()
    }
}