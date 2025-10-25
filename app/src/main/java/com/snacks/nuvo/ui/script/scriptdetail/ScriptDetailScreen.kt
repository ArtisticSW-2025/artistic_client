package com.snacks.nuvo.ui.script.scriptdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.Routes
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.home.FakeCallSessionRepository
import com.snacks.nuvo.ui.script.ScriptScreen
import com.snacks.nuvo.ui.script.ScriptViewModel
import com.snacks.nuvo.ui.script.component.ScriptContentCard
import com.snacks.nuvo.ui.script.component.ScriptGoalCard
import com.snacks.nuvo.ui.script.component.ScriptIconToggleButton
import com.snacks.nuvo.ui.script.component.ScriptMissionCard
import com.snacks.nuvo.ui.script.component.ScriptStartButton
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptDetailScreen(
    appState: NuvoAppState,
    viewModel: ScriptDetailViewModel = hiltViewModel(),
    id: String?,
    isSmallTalkMode: Boolean,
    isEmergencyMode: Boolean,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id, isSmallTalkMode, isEmergencyMode) {
        viewModel.initScreen(
            id = id,
            isSmallTalkMode = isSmallTalkMode,
            isEmergencyMode = isEmergencyMode
        )
    }

    if (uiState.id != null) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .background(NuvoTheme.colors.gray1)
        ) {
            Box(
                modifier = Modifier
                    .background(NuvoTheme.colors.mainGreen)
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 36.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { appState.navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = NuvoTheme.colors.white,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Spacer(Modifier.height(22.dp))

                    Spacer(Modifier.height(60.dp))
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.End
//                    ) {
//                        ScriptIconToggleButton(
//                            icon = R.drawable.ic_users_outlined,
//                            isSelected = uiState.isSmallTalkMode
//                        ) { }
//                        Spacer(Modifier.width(16.dp))
//                        ScriptIconToggleButton(
//                            icon = R.drawable.ic_danger_outlined,
//                            isSelected = uiState.isEmergencyMode
//                        ) {}
//                    }

                    Spacer(Modifier.height(76.dp))
                    Text(
                        uiState.title,
                        style = NuvoTheme.typography.pretendardBlack32,
                        color = NuvoTheme.colors.white
                    )
                    Spacer(Modifier.height(48.dp))
                    ScriptStartButton(
                        label = "START",
                        onClick = {
                            val prevName = uiState.title
                            val callSessionId = uiState.id
                            val contactName = uiState.contactName

                            val route = "${Routes.Call.ROUTE}?" +
                                    "prevName=$prevName&" +
                                    "callSessionId=$callSessionId&" +
                                    "contactName=$contactName"

                            appState.navController.navigate(route)
                        }
                    )
                }
            }
            Spacer(Modifier.height(32.dp))
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                ScriptMissionCard(
                    mission = uiState.mission
                )
                Spacer(Modifier.height(28.dp))
                ScriptGoalCard(
                    goal = uiState.goal
                )
                Spacer(Modifier.height(28.dp))
                ScriptContentCard(
                    dialogues = uiState.dialogues
                )
            }
            Spacer(Modifier.height(78.dp))
        }
    }
    if (uiState.isLoading) {
        LoadingIndicator()
    }
}

@Preview
@Composable
fun ScriptDetailScreenPreview() {
    val viewModel = remember { ScriptDetailViewModel(
        callSessionRepository = FakeCallSessionRepository()
    ) }
    ScriptDetailScreen(
        appState = rememberNuvoAppState(),
        viewModel = viewModel,
        id = "",
        isSmallTalkMode = true,
        isEmergencyMode = false
    )
}