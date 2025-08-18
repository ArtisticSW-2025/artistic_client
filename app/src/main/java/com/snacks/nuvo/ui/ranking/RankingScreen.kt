package com.snacks.nuvo.ui.ranking

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.ranking.component.ProfileImage
import com.snacks.nuvo.ui.ranking.component.RankingCard
import com.snacks.nuvo.ui.ranking.component.RankingHeader
import com.snacks.nuvo.ui.ranking.component.RankingList
import com.snacks.nuvo.ui.theme.NUVOTheme
import com.snacks.nuvo.ui.theme.NuvoColors
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
    viewModel: RankingViewModel = viewModel()
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
            RankingHeader(emptyList())
            LoadingState()
        }
        else{
            RankingHeader(uiState.topThreeRankings)
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