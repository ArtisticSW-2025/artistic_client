package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.ranking.RankingConstants
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RankingCard(
    rank: Int,
    name: String,
    score: Int,
    profileImageUrl: String? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .background(
                NuvoTheme.colors.white,
                shape = RoundedCornerShape(RankingConstants.CardCornerRadius)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = rank.toString(),
                style = NuvoTheme.typography.interBold18,
                color = NuvoTheme.colors.mainGreen,
                modifier = Modifier.width(30.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(16.dp))

            ProfileImage(
                imageUrl = profileImageUrl,
                size = RankingConstants.CardImageSize
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = name,
                style = NuvoTheme.typography.interBold18,
                color = NuvoTheme.colors.subNavy,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(12.dp))

            ScoreSection(score = score)
        }
    }
}

@Composable
internal fun ScoreSection(score: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_flag_filled),
            tint = NuvoTheme.colors.subLightGreen,
            contentDescription = "Score flag"
        )

        Text(
            text = score.toString(),
            style = NuvoTheme.typography.interRegular15,
            color = NuvoTheme.colors.gray5,
            modifier = Modifier.width(40.dp)
        )
    }
}

@Composable
internal fun ProfileImage(
    imageUrl: String?,
    size: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(NuvoTheme.colors.gray3)
    ) {
    }
}
