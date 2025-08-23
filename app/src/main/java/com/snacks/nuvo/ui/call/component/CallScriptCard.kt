package com.snacks.nuvo.ui.call.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun CallScriptCard(
    modifier: Modifier,
    script: String,
    isAI: Boolean,
    isLast: Boolean,
) {
    Card(
        modifier = modifier
            .height(120.dp)
            .width(350.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(color = if (isLast) NuvoTheme.colors.white else NuvoTheme.colors.white.copy(alpha = 0.5f))
                    .fillMaxSize(),
            )
            Box(
                modifier = Modifier
                    .background(color = if (isLast) NuvoTheme.colors.white else NuvoTheme.colors.white.copy(alpha = 0.5f))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    script,
                    style = NuvoTheme.typography.interMedium15.copy(color = if (isAI) NuvoTheme.colors.mainGreen else NuvoTheme.colors.subNavy)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF53A790)
@Composable
internal fun CallScriptCardPreview() {
    CallScriptCard(
        modifier = Modifier,
        script = "안녕하세요, 힐링내과입니다.\n" +
                "무엇을 도와드릴까요?",
        isAI = true,
        isLast = false
    )
}

@Preview
@Composable
internal fun CallScriptCardPreview2() {
    CallScriptCard(
        modifier = Modifier,
        script = "안녕하세요.\n" +
                "초진 예약을 하고 싶어서 전화드렸어요.",
        isAI = false,
        isLast = true
    )
}

@Preview
@Composable
internal fun CallScriptCardPreview3() {
    CallScriptCard(
        modifier = Modifier,
        script = "네, 증상은 어떤 것이신가요?",
        isAI = true,
        isLast = true
    )
}