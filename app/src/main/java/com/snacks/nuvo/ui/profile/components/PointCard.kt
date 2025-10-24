package com.snacks.nuvo.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow

@Composable
fun PointCard(
    modifier: Modifier = Modifier,
    points: String,
) {
    Box(
        modifier = modifier
            .height(68.dp)
            .padding(horizontal = 20.dp)
            .dropShadow(
                shape = RoundedCornerShape(
                    size = 15.dp,
                ),
                offsetX = 2.dp,
                offsetY = 4.dp,
                blur = 9.dp,
                spread = 0.dp,
                color = NuvoTheme.colors.black.copy(alpha = 0.25f),
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 15.dp))
                .fillMaxSize()
                .background(color = NuvoTheme.colors.mainGreen)
        ) { }

        Row(
            modifier = Modifier.width(316.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "나의 포인트",
                style = NuvoTheme.typography.pretendardMedium15,
                lineHeight = 18.sp,
                color = NuvoTheme.colors.white
            )

            Text(
                text = points,
                style = NuvoTheme.typography.pretendardSemiBold26,
                lineHeight = 31.sp,
                color = NuvoTheme.colors.white
            )
        }

    }
}