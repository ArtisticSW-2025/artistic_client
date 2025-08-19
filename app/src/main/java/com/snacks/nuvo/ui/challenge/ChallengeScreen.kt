package com.snacks.nuvo.ui.challenge

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.ui.challenge.component.DatePhraseCard
import com.snacks.nuvo.ui.challenge.component.WeeklyMissionCard
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme

@SuppressLint("NewApi")
@Preview
@Composable
internal fun ChallengeScreen(
    viewModel: ChallengeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp


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
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DatePhraseCard(
                modifier = Modifier,
                node = uiState.selectedNode,
            )
            Spacer(Modifier.height(26.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                WeeklyMissionCard(
                    modifier = Modifier,
                    mission = uiState.weeklyMission
                ) {

                }
            }

        }



        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}