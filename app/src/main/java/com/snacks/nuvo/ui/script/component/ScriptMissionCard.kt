package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptMissionCard(
    modifier: Modifier = Modifier,
    mission: String,
) {
    ScriptDetailCard(
        modifier = modifier.padding(vertical = 16.dp, horizontal = 20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .width(125.dp)
                    .height(26.dp),
                color = NuvoTheme.colors.subLightGreen,
                shape = RoundedCornerShape(13.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        "Mission",
                        style = NuvoTheme.typography.interMedium15,
                        color = NuvoTheme.colors.white
                    )
                }
            }
            Spacer(Modifier.height(12.dp))
            Text(
                mission,
                style = NuvoTheme.typography.interSemiBold15,
                color = NuvoTheme.colors.subNavy
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScriptMissionCardPreview() {
    ScriptMissionCard(
        mission = "병원 초진 예약 전화를 성공적으로 완료하세요!"
    )
}