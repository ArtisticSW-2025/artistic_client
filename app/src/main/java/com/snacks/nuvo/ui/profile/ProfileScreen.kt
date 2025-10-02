package com.snacks.nuvo.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.ui.profile.components.FeedbackSection
import com.snacks.nuvo.ui.profile.components.PointCard
import com.snacks.nuvo.ui.profile.components.ProfileHeader
import com.snacks.nuvo.ui.profile.components.StatsCard
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ProfileScreenContent(
        uiState = uiState,
        onEditProfileClick = viewModel::onEditProfileClick
    )
}

@Composable
private fun ProfileScreenContent(
    uiState: ProfileUiState,
    onEditProfileClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.white),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader(
            userName = uiState.userName,
            onEditClick = onEditProfileClick
        )

        PointCard(points = uiState.points)

        StatsCard(
            completedMissions = uiState.completedMissions,
            totalSpeakingTime = uiState.totalSpeakingTime
        )

        FeedbackSection(feedbackItems = uiState.feedbackItems)
    }
}

@Preview
@Composable
internal fun ProfileScreenPreview() {
    ProfileScreenContent(
        uiState = ProfileUiState(),
        onEditProfileClick = {}
    )
}