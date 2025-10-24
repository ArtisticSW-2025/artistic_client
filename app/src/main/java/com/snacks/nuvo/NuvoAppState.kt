package com.snacks.nuvo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberNuvoAppState(
    navController: NavHostController = rememberNavController(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    scope: CoroutineScope = rememberCoroutineScope()
): NuvoAppState {
    return remember(navController, snackbarHostState, scope) {
        NuvoAppState(navController, snackbarHostState, scope)
    }
}

class NuvoAppState(
    val navController: NavHostController,
    val snackbarHostState: SnackbarHostState,
    val scope: CoroutineScope,
) {
    // 현재 루트
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations = TopLevelDestination.entries

    // 현재 루트가 TopLevel일 때만 BottomNavBar 노출
    val shouldShowBottomBar: Boolean
        @Composable get() {
            val currentRoute = currentDestination?.route ?: return false
            return topLevelDestinations.any { it.route == currentRoute }
        }

    // 스낵바
    fun showSnackBar(message: String) {
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }

    // 라우팅
    fun navigate(
        destination: String,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null,
    ) {
        navController.navigate(destination, navOptions, navigatorExtras)
    }

    // BottomNavBar 라우팅
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        navController.navigate(topLevelDestination.route, topLevelNavOptions)
    }
}