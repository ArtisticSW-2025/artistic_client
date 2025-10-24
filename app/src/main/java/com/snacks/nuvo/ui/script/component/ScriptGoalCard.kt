package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptGoalCard(
    modifier: Modifier = Modifier,
    goal: String,
) {
    ScriptDetailCard(
        modifier = modifier.padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                "스크립트 목표",
                style = NuvoTheme.typography.pretendardSemiBold15,
                color = NuvoTheme.colors.mainGreen
            )
            Spacer(Modifier.height(12.dp))
            Text(
                goal,
                style = NuvoTheme.typography.interMedium15.copy(lineHeight = 20.sp),
                color = NuvoTheme.colors.gray5
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScriptGoalCardPreveiw() {
    ScriptGoalCard(
        goal = "• 병원에 인사하고, 초진 예약하고 싶다고 말하기\n• 증상 간단히 설명하기\n• 가능한 날짜/시간 물어보기\n• 예약 확정 및 마무리 인사하기"
    )
}