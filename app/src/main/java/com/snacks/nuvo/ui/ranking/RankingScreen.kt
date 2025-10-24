package com.snacks.nuvo.ui.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.ranking.component.RankingHeader
import com.snacks.nuvo.ui.ranking.component.RankingList
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RankingScreen(
    viewModel: RankingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    RankingContent(
        uiState = uiState,
    )
}

@Composable
internal fun RankingContent(
    uiState: RankingUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(NuvoTheme.colors.gray1)
    ) {

        RankingHeader(topThree = uiState.topThreeRankings)

        RankingList(rankings = uiState.remainingRankings)

        if (uiState.isLoading){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoadingIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RankingScreenPreview() {
    val viewModel = remember { RankingViewModel(
        rankingRepository = FakeRankingRepository()
    ) }
    RankingScreen(
        viewModel = viewModel
    )
}