package com.snacks.nuvo.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.Routes
import com.snacks.nuvo.TopLevelDestination
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.call.FakeUserRepository
import com.snacks.nuvo.ui.challenge.FakeMissionRecordRepository
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.home.component.RecommendScriptCard
import com.snacks.nuvo.ui.home.component.TodayMissionCard
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.toCommaFormat

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
internal fun HomeScreen(
    appState: NuvoAppState,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val lazyListState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.gray1),
        contentAlignment = Alignment.Center,
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                NuvoTheme.colors.lightGradient1,
                                NuvoTheme.colors.lightGradient2
                            )
                        )
                    )
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .requiredWidth(screenWidth * 1.55f)
                        .offset(y = 120.dp, x = screenWidth * 0.01f),
                    painter = painterResource(R.mipmap.home_character),
                    contentDescription = null
                )

                Column(
                    modifier = Modifier
                        .padding(top = 60.dp, bottom = 120.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
                        Text(
                            "HELLO, ",
                            style = NuvoTheme.typography.pretendardSemiBold24.copy(color = NuvoTheme.colors.white),
                            lineHeight = 29.sp
                        )
                        Text(
                            uiState.userName,
                            style = NuvoTheme.typography.pretendardBlack24.copy(color = NuvoTheme.colors.white),
                            lineHeight = 29.sp
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_flag_filled),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(
                                NuvoTheme.colors.subLemon
                            )
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            "RANK ${uiState.rank.toCommaFormat()}",
                            style = NuvoTheme.typography.pretendardMedium15.copy(color = NuvoTheme.colors.white)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        uiState.score.toCommaFormat(),
                        style = NuvoTheme.typography.pretendardBlack48.copy(color = NuvoTheme.colors.white)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(NuvoTheme.colors.gray2)
                    .zIndex(2f)
            ) {

                Box(
                    modifier = Modifier
                        .padding(horizontal = 34.dp)
                        .offset(y = -20.dp)
                        .zIndex(2f)
                ) {
                    TodayMissionCard(
                        modifier = Modifier,
                        mission = uiState.todayMission,
                        onClick = { appState.navigateToTopLevelDestination(TopLevelDestination.Challenge) }
                    )
                }

                Spacer(Modifier.height(15.dp))

                Column {
                    Text(
                        "추천 스크립트",
                        style = NuvoTheme.typography.pretendardBlack16.copy(color = NuvoTheme.colors.mainGreen),
                        modifier = Modifier.padding(start = 36.dp, bottom = 12.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .height(130.dp)
                            .fillMaxWidth(),
                        state = lazyListState,
                        flingBehavior = flingBehavior,
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(start = 21.dp, end = 47.dp)
                    ) {
                        items(uiState.recommendScripts) { script ->
                            RecommendScriptCard(
                                modifier = Modifier.width(screenWidth - 68.dp),
                                title = script.title,
                                description = script.description,
                                onClick = { appState.navigate("${Routes.Script.SCRIPT_DETAIL}/${script.id}?isSmallTalkMode=false&isEmergencyMode=false") }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(36.dp))

            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val viewModel = remember { HomeViewModel(
        userRepository = FakeUserRepository(),
        callSessionRepository = FakeCallSessionRepository(),
        missionRecordRepository = FakeMissionRecordRepository(),
    ) }
    HomeScreen(
        appState = rememberNuvoAppState(),
        viewModel = viewModel
    )
}