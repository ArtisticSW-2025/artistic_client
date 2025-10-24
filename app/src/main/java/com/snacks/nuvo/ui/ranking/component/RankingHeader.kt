package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.ranking.RankingItem
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RankingHeader(
    modifier: Modifier = Modifier,
    topThree: List<RankingItem>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = NuvoTheme.colors.mainGreen,
                shape = RoundedCornerShape(
                    bottomEnd = 40.dp,
                    bottomStart = 40.dp
                )
            )
    ) {
        Spacer(modifier = Modifier.height(46.dp))

        Text(
            text = "RANKING",
            color = NuvoTheme.colors.white,
            style = NuvoTheme.typography.interBlack24,
            modifier = Modifier
                .padding(start = 20.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        if (topThree.isNotEmpty()) {
            TopThreeRankers(
                topThree = topThree,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.height(52.dp))
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
        if (topThree.size > 1) {
            TopRankerItem(
                ranker = topThree[1],
                imageSize = 90.dp,
            )
        }
        if (topThree.isNotEmpty()) {
            TopRankerItem(
                ranker = topThree[0],
                imageSize = 108.dp,
                isWinner = true
            )
        }
        if (topThree.size > 2) {
            TopRankerItem(
                ranker = topThree[2],
                imageSize = 90.dp,
            )
        }
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
        modifier = modifier,
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
            modifier = Modifier.widthIn(max = 100.dp)
        )

        Text(
            text = ranker.score.toString(),
            color = NuvoTheme.colors.white,
            style = NuvoTheme.typography.interMedium15,
        )
    }
}
