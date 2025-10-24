package com.snacks.nuvo.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.profile.components.FeedbackSection
import com.snacks.nuvo.ui.profile.components.PointCard
import com.snacks.nuvo.ui.profile.components.ProfileHeader
import com.snacks.nuvo.ui.profile.components.StatsCard
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun ProfileScreen(
    appState: NuvoAppState,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.logoutEvent.collect {
            appState?.navController?.navigate(Routes.Login.ROUTE) {
                popUpTo(Routes.Home.ROUTE) { inclusive = true }
            }
        }
    }

    ProfileScreenContent(
        uiState = uiState,
        onEditProfileClick = viewModel::onEditProfileClick,
        onLogoutClick = viewModel::onLogoutClick
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    uiState: ProfileUiState,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    var openBottomSheet by remember { mutableStateOf(false) }
    var skipPartiallyExpanded by remember { mutableStateOf(true) }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    var selectedFeedback by remember { mutableStateOf<FeedbackData?>(null) }

    val scrollState = rememberScrollState()
    var topHeightPx by remember { mutableStateOf(0) }

    var isMenuExpanded by remember { mutableStateOf(false) }

    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        val fullHeightDp = maxHeight

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NuvoTheme.colors.white)
                    .onGloballyPositioned { coordinates ->
                        topHeightPx = coordinates.size.height
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                ProfileHeader(
                    userName = uiState.userName,
                    onEditClick = onEditProfileClick
                )

                Spacer(modifier = Modifier.height(34.dp))

                PointCard(points = uiState.points)

                Spacer(modifier = Modifier.height(20.dp))

                StatsCard(
                    modifier = Modifier,
                    completedMissions = uiState.completedMissions,
                    totalSpeakingTime = uiState.totalSpeakingTime
                )

                Spacer(modifier = Modifier.height(50.dp))
            }

            val topHeightDp = with(density) { topHeightPx.toDp() }

            FeedbackSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = fullHeightDp - topHeightDp),
                feedbackItems = uiState.feedbackItems,
                onClick = { feedbackItem ->
                    selectedFeedback = feedbackItem
                    openBottomSheet = true
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Box {
                IconButton(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(size = 20.dp),
                    onClick = { isMenuExpanded = true },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings_filled),
                        tint = NuvoTheme.colors.gray3,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    modifier = Modifier.background(NuvoTheme.colors.white),
                    expanded = isMenuExpanded,
                    onDismissRequest = { isMenuExpanded = false },
                ) {
                    DropdownMenuItem(
                        text = { Text("로그아웃") },
                        onClick = {
                            onLogoutClick()
                            isMenuExpanded = false
                        }
                    )
                }
            }
        }

        if (openBottomSheet && selectedFeedback != null) {
            ModalBottomSheet(
                onDismissRequest = { openBottomSheet = false },
                sheetState = bottomSheetState,
                containerColor = NuvoTheme.colors.white,
            ) {
                FeedbackCardSheet(
                    modifier = Modifier,
                    feedbackData = selectedFeedback!!
                )
            }
        }
    }
}

@Preview
@Composable
internal fun ProfileScreenPreview() {
    ProfileScreenContent(
        uiState = ProfileUiState(),
        onEditProfileClick = {},
        onLogoutClick = {}
    )
}