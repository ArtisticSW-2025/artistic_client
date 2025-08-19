package com.snacks.nuvo.ui.challenge.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow

@Composable
internal fun WeeklyMissionCard(
    modifier: Modifier,
    mission: String,
    onClick: () -> Unit,
) {
    Card (
        modifier = modifier
            .height(height = 175.dp)
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(
                    size = 20.dp,
                ),
                offsetX = 2.dp,
                offsetY = 4.dp,
                blur = 9.dp,
                spread = 0.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(
            size = 20.dp,
        ),
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Transparent,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = NuvoTheme.colors.white.copy(alpha = 0.9f))
                    .blur(radius = 20.dp),
                contentAlignment = Alignment.Center,
            ) { }

            Box(
                modifier = Modifier
                    .padding(horizontal = 26.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(34.dp),
                            painter = painterResource(id = R.drawable.ic_shield_fiiled),
                            contentDescription = null,
                        )
                        Spacer(Modifier.width(13.dp))
                        Column {
                            Text(
                                text = "주간 미션",
                                style = NuvoTheme.typography.interBlack20.copy(color = NuvoTheme.colors.subNavy)
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = mission,
                                style = NuvoTheme.typography.interMedium15.copy(color = NuvoTheme.colors.subNavy)
                            )
                        }
                    }

                    Spacer(Modifier.height(26.dp))

                    Button(
                        modifier = Modifier
                            .height(height = 47.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(),
                        onClick = onClick,
                    ) {
                        Box (
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            NuvoTheme.colors.lightGradient1,
                                            NuvoTheme.colors.lightGradient2
                                        )
                                    ),
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "START",
                                style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.white)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
internal fun PreviewWeeklyMissionCard() {
    WeeklyMissionCard(
        modifier = Modifier,
        mission = "30분 이상 통화하기",
    ) { }
}