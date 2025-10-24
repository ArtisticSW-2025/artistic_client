package com.snacks.nuvo.ui.ranking.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow

@Composable
internal fun RankingCard(
    modifier: Modifier = Modifier,
    rank: Int,
    name: String,
    score: Int,
    profileImageUrl: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .dropShadow(
                shape = RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
                offsetX = 0.dp,
                offsetY = 0.dp,
                blur = 10.dp,
                spread = 0.dp,
                color = NuvoTheme.colors.black.copy(alpha = 0.15f),
            )
            .background(
                NuvoTheme.colors.white,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.width(30.dp),
                text = rank.toString(),
                style = NuvoTheme.typography.interBold18,
                color = NuvoTheme.colors.mainGreen,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.width(20.dp))

            ProfileImage(
                modifier = Modifier,
                imageUrl = profileImageUrl,
                size = 37.dp
            )

            Spacer(modifier = Modifier.width(28.dp))

            Text(
                text = name,
                style = NuvoTheme.typography.interBold18,
                color = NuvoTheme.colors.subNavy,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.width(8.dp))

            ScoreSection(score = score)
        }
    }
}

@Composable
internal fun ScoreSection(
    modifier: Modifier = Modifier,
    score: Int
) {
    Row(
        modifier = modifier,
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
internal fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    size: androidx.compose.ui.unit.Dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = NuvoTheme.colors.gray3,
                shape = CircleShape
            )
            .background(NuvoTheme.colors.white),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_user_icon),
            contentDescription = null,
            modifier = modifier
                .size(size - 4.dp)
        )
    }
}
