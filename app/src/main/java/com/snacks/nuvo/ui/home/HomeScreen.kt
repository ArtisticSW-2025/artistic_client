package com.snacks.nuvo.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.home.component.RecommendScriptCard
import com.snacks.nuvo.ui.home.component.TodayMissionCard
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.toCommaFormat

@SuppressLint("ConfigurationScreenWidthHeight")
@Preview
@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.gray1),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
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
                        .offset(y = 20.dp),
                    painter = painterResource(R.mipmap.home_character),
                    contentDescription = null
                )
                Column(
                    modifier = Modifier
                        .padding(top = 60.dp, bottom = 90.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row {
                        Text(
                            "HELLO, ",
                            style = NuvoTheme.typography.interSemiBold24.copy(color = NuvoTheme.colors.white)
                        )
                        Text(
                            uiState.userName,
                            style = NuvoTheme.typography.interBlack24.copy(color = NuvoTheme.colors.white)
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
                            style = NuvoTheme.typography.interMedium15.copy(color = NuvoTheme.colors.white)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        uiState.score.toCommaFormat(),
                        style = NuvoTheme.typography.interBlack48.copy(color = NuvoTheme.colors.white)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = NuvoTheme.colors.gray1)
                    .padding(top = 104.dp)
            ) {
                Column {
                    Text(
                        "추천 스크립트",
                        style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.mainGreen),
                        modifier = Modifier.padding(start = 36.dp, bottom = 12.dp)
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        contentPadding = PaddingValues(start = 20.dp, end = 20.dp)
                    ) {
                        items(uiState.recommendScripts) { script ->
                            RecommendScriptCard(
                                modifier = Modifier,
                                title = script.title,
                                description = script.description
                            ) {}
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp)
                .offset(y = screenHeight * 0.5f - 30.dp)
        ) {
            TodayMissionCard(
                modifier = Modifier,
                mission = uiState.todayMission
            ) { }
        }
        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}