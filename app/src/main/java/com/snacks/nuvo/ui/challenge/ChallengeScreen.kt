package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.challenge.component.CourseMap
import com.snacks.nuvo.ui.challenge.component.DatePhraseCard
import com.snacks.nuvo.ui.challenge.component.TodayMissionDialog
import com.snacks.nuvo.ui.challenge.component.WeeklyMissionCard
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun ChallengeScreen(
    appState: NuvoAppState,
    viewModel: ChallengeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val hazeState = rememberHazeState()
    val firstPosition: MutableState<Int> = remember { mutableIntStateOf(0) }
    val density = LocalDensity.current

    LaunchedEffect(firstPosition) {
        val calculatedPosition = firstPosition.value - with(density) { 350.dp.toPx() }.toInt()
        scrollState.scrollTo(calculatedPosition)
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
         viewModel.loadChallengeData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .hazeSource(state = hazeState, zIndex = 1f),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState, zIndex = 0f),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colorStops  = arrayOf(
                                0.0f to NuvoTheme.colors.darkGradient1,
                                0.4f to NuvoTheme.colors.lightGradient2,
                                0.98f to NuvoTheme.colors.white,
                            )
                        )
                    ),
            )
            CourseMap(
                nodes = uiState.nodeList,
                modifier = Modifier
                    .width(width = 319.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
                onNodeClick = { nodeId ->
                    viewModel.onNodeClicked(nodeId)
                },
                firstPosition = firstPosition
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DatePhraseCard(
                modifier = Modifier,
                hazeState = hazeState,
                date = uiState.today,
                phrase = uiState.phrase,
            )
            Spacer(Modifier.height(26.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                WeeklyMissionCard(
                    modifier = Modifier,
                    hazeState = hazeState,
                    mission = uiState.weeklyMission
                ) {

                }
            }

        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }

    if (uiState.selectedNode !== null) {
        TodayMissionDialog(
            modifier = Modifier
                .hazeSource(state = hazeState, zIndex = 2f),
            hazeState = hazeState,
            date = uiState.today,
            title = uiState.todayMissionTitle,
            onConfirm = {
                val prevName = "오늘의 미션"
                val isTodayMission = true
                val todayMission = uiState.todayMissionDescription
                val todayMissionDateString = uiState.today.toString()

                val route = "${Routes.Call.ROUTE}?" +
                        "prevName=$prevName&" +
                        "isTodayMission=$isTodayMission&" +
                        "todayMission=$todayMission&" +
                        "todayMissionDateString=$todayMissionDateString"

                appState.navController.navigate(route)

                viewModel.clearSelectedNode()
            } ,
            onDismiss = { viewModel.clearSelectedNode() },
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ChallengeScreenPreview() {
    ChallengeScreen(appState = rememberNuvoAppState())
}