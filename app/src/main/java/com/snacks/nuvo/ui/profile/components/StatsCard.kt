package com.snacks.nuvo.ui.profile.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.profile.components.InfoRow
import com.snacks.nuvo.ui.profile.components.ShadowCard
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun StatsCard(
    completedMissions: String,
    totalSpeakingTime: String,
    modifier: Modifier = Modifier
) {
    ShadowCard(
        modifier = modifier.height(89.dp)
    ) {
        Column {
            StatRow(
                label = "총 미션 완료 수",
                icon = painterResource(id = R.drawable.ic_check_outlined),
                value = completedMissions,
                padding = 48.dp
            )

            Spacer(modifier = Modifier.height(10.dp))

            StatRow(
                label = "누적 말하기 시간",
                icon = painterResource(id = R.drawable.ic_timer_outlined),
                value = totalSpeakingTime,
                padding = 14.dp
            )
        }
    }
    Spacer(modifier = Modifier.height(30.dp))
}

@Composable
private fun StatRow(
    label: String,
    icon: Painter,
    value: String,
    padding: Dp
) {
    InfoRow(
        leftText = label,
        leftTextStyle = NuvoTheme.typography.interMedium15,
        leftTextColor = NuvoTheme.colors.gray5,
        rightContent = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = icon,
                    tint = NuvoTheme.colors.subLightGreen,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(
                    text = value,
                    style = NuvoTheme.typography.interBlack14,
                    color = NuvoTheme.colors.subNavy
                )
                Spacer(Modifier.width(padding))
            }
        }
    )
}