package com.snacks.nuvo.ui.profile.components


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun PointCard(
    modifier: Modifier = Modifier,
    points: String,
) {
    InfoCard(
        backgroundColor = NuvoTheme.colors.mainGreen,
        modifier = modifier.height(68.dp)
    ) {
        InfoRow(
            leftText = "나의 포인트",
            leftTextStyle = NuvoTheme.typography.interMedium15,
            leftTextColor = NuvoTheme.colors.white,
            rightText = points,
            rightTextStyle = NuvoTheme.typography.interSemiBold26,
            rightTextColor = NuvoTheme.colors.white
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}