package com.snacks.nuvo.ui.ranking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.ui.ranking.component.RankingHeader
import com.snacks.nuvo.ui.ranking.component.RankingList
import com.snacks.nuvo.ui.theme.NuvoTheme

// Constants
object RankingConstants {
    val HeaderHeight = 360.dp
    val TopRankerImageSize = 108.dp
    val SecondThirdImageSize = 90.dp
    val CardImageSize = 33.dp
    val CardCornerRadius = 10.dp
    val HeaderCornerRadius = 40.dp
}



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

        if(uiState.isLoading){
            RankingHeader(topThree= emptyList())
            LoadingState()
        }
        else{
            RankingHeader(topThree = uiState.topThreeRankings)
            Spacer(modifier = Modifier.height(20.dp))
            RankingList(
                rankings = uiState.remainingRankings)
        }
    }
}
@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CircularProgressIndicator(
                color = NuvoTheme.colors.mainGreen
            )
        }
    }
}





@Preview(showBackground = true)
@Composable
fun PreviewRankingScreen() {
    RankingScreen()
}