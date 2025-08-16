package com.snacks.nuvo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import com.snacks.nuvo.ui.home.homeGraph
import com.snacks.nuvo.ui.challenge.challengeGraph
import com.snacks.nuvo.ui.component.BottomNavigationBar
import com.snacks.nuvo.ui.component.BottomNavigationBarItem
import com.snacks.nuvo.ui.profile.profileGraph
import com.snacks.nuvo.ui.ranking.rankingGraph
import com.snacks.nuvo.ui.script.scriptGraph

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NuvoNavHost(appState: NuvoAppState) {
    val navController = appState.navController

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = {
            Surface(
                color = Color.White
            ) {
                AnimatedVisibility(
                    visible = appState.shouldShowBottomBar,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it },
                ) {
                    NuvoBottomNavigationBar(
                        destinations = appState.topLevelDestinations,
                        currentDestination = appState.currentDestination,
                        onNavigateToDestination = { destination ->
                            appState.navigateToTopLevelDestination(destination)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Routes.Home.ROUTE,
            modifier = Modifier.padding(paddingValues),
        ) {
            homeGraph()
            scriptGraph()
            rankingGraph()
            challengeGraph()
            profileGraph()
        }
    }
}

@Composable
private fun NuvoBottomNavigationBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    currentDestination: NavDestination?,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
) {
    BottomNavigationBar(
        modifier = modifier,
    ) {
        destinations.forEach { destination ->
            val isSelected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            BottomNavigationBarItem(
                label = destination.label,
                selected = isSelected,
                selectedIcon = destination.selectedIcon,
                unselectedIcon = destination.unselectedIcon,
                onClick = { onNavigateToDestination(destination) }
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) == true
    } == true