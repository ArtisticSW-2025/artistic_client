package com.snacks.nuvo.ui.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.Routes

fun NavGraphBuilder.profileGraph() {
    composable(
        route = Routes.Profile.ROUTE
    ) {
        ProfileScreen()
    }
}