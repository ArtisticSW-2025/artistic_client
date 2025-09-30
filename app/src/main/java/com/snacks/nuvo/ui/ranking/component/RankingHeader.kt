package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.ui.ranking.RankingConstants
import com.snacks.nuvo.ui.ranking.RankingItem
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RankingHeader(modifier: Modifier = Modifier,
                           topThree: List<RankingItem>) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(RankingConstants.HeaderHeight)
            .background(
                color = NuvoTheme.colors.mainGreen,
                shape = RoundedCornerShape(
                    bottomEnd = RankingConstants.HeaderCornerRadius,
                    bottomStart = RankingConstants.HeaderCornerRadius
                )
            )
    ) {
        Text(
            text = "RANKING",
            color = NuvoTheme.colors.white,
            style = NuvoTheme.typography.interBlack24,
            fontSize = 24.sp,
            fontWeight = FontWeight.W900,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 60.dp)
        )

        if (topThree.isNotEmpty()) {
            TopThreeRankers(
                topThree = topThree,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 52.dp,)
            )
        }
    }
}


@Composable
internal fun TopThreeRankers(
    modifier: Modifier = Modifier,
    topThree: List<RankingItem>,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        TopRankerItem(
            ranker = topThree[1],
            imageSize = RankingConstants.SecondThirdImageSize,
        )
        TopRankerItem(
            ranker = topThree[0],
            imageSize = RankingConstants.TopRankerImageSize,
            isWinner = true
        )
        TopRankerItem(
            ranker = topThree[2],
            imageSize = RankingConstants.SecondThirdImageSize,
        )

    }
}

@Composable
internal fun TopRankerItem(
    modifier: Modifier = Modifier,
    ranker: RankingItem,
    imageSize: androidx.compose.ui.unit.Dp,
    isWinner: Boolean = false
) {
    Column(
        modifier=modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = ranker.rank.toString(),
            style = if (isWinner) NuvoTheme.typography.interBold18 else NuvoTheme.typography.interBold16,
            color = NuvoTheme.colors.white,
        )
        Spacer(modifier = Modifier.height(4.dp))

        ProfileImage(
            imageUrl = ranker.profileImageUrl,
            size = imageSize
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = ranker.name,
            style = NuvoTheme.typography.interBold16,
            color = NuvoTheme.colors.white,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.widthIn(max = 80.dp) // 최대 너비 제한
        )

        Text(
            text = ranker.score.toString(),

            color = NuvoTheme.colors.white,
            style = NuvoTheme.typography.interMedium15,
        )
    }
}
