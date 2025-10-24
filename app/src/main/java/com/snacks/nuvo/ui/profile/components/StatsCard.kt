package com.snacks.nuvo.ui.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow

@Composable
fun StatsCard(
    modifier: Modifier = Modifier,
    completedMissions: String,
    totalSpeakingTime: String,
) {
    Box(
        modifier = modifier
            .height(89.dp)
            .padding(horizontal = 20.dp)
            .dropShadow(
                shape = RoundedCornerShape(
                    size = 15.dp,
                ),
                offsetX = 2.dp,
                offsetY = 2.dp,
                blur = 9.dp,
                spread = 0.dp,
                color = NuvoTheme.colors.black.copy(alpha = 0.2f),
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(size = 15.dp))
                .fillMaxSize()
                .background(color = NuvoTheme.colors.white)
        ) { }

        Column (
            modifier = Modifier.width(332.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                        .width(159.dp),
                    text="총 미션 완료 수",
                    style = NuvoTheme.typography.pretendardMedium14,
                    color = NuvoTheme.colors.gray5,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.width(72.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_outlined),
                    tint = NuvoTheme.colors.mainGreen,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = completedMissions,
                    style = NuvoTheme.typography.pretendardBlack14,
                    color = NuvoTheme.colors.subNavy,
                    lineHeight = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier
                    .width(159.dp),
                    text="누적 말하기 시간",
                    style = NuvoTheme.typography.pretendardMedium14,
                    color = NuvoTheme.colors.gray5,
                    lineHeight = 17.sp
                )
                Spacer(modifier = Modifier.width(72.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_timer_outlined),
                    tint = NuvoTheme.colors.mainGreen,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = totalSpeakingTime,
                    style = NuvoTheme.typography.pretendardBlack14,
                    color = NuvoTheme.colors.subNavy,
                    lineHeight = 17.sp
                )
            }
        }
    }
}