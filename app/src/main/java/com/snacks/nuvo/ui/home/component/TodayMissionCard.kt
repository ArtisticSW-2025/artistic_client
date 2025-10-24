package com.snacks.nuvo.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.component.NoRippleInteractionSource
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow

@Composable
internal fun TodayMissionCard(
    modifier: Modifier,
    mission: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable(
                interactionSource = remember { NoRippleInteractionSource() },
                indication = null
            ) { onClick() }
            .dropShadow(
                shape = RoundedCornerShape(
                    size = 15.dp,
                ),
                offsetX = 0.dp,
                offsetY = 6.dp,
                blur = 12.dp,
                spread = 0.dp,
                color = NuvoTheme.colors.black.copy(alpha = 0.12f),
            ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NuvoTheme.colors.white)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star_filled),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp),
                    colorFilter = ColorFilter.tint(
                        NuvoTheme.colors.mainGreen
                    )
                )
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        "오늘의 미션",
                        style = NuvoTheme.typography.pretendardBlack18.copy(color = NuvoTheme.colors.mainGreen)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        mission,
                        style = NuvoTheme.typography.pretendardMedium12.copy(color = NuvoTheme.colors.subLightGreen)
                    )
                    Spacer(Modifier.height(4.dp))
                }
                Spacer(Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_right),
                    contentDescription = null,
                )
            }
        }
    }
}