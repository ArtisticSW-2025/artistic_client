package com.snacks.nuvo.ui.call.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.snacks.nuvo.ui.call.WaveformMode
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun WaveformComponent(
    modifier: Modifier = Modifier,
    mode: WaveformMode = WaveformMode.MINIMAL,
    realtimeLevels: List<Float> = emptyList(),
    barColor: Color = NuvoTheme.colors.white,
    canvasWidth: Dp = 350.dp,
    canvasHeight: Dp = 70.dp,
    barWidth: Dp = 9.dp,
    gapWidth: Dp = 15.dp,
    maxLinesCount: Int = 14
) {
    Canvas(
        modifier = modifier
            .width(canvasWidth)
            .height(canvasHeight)
    ) {
        val barCount = (size.width / (barWidth.toPx() + gapWidth.toPx()))
            .toInt()
            .coerceAtMost(maxLinesCount)

        val animatedVolumeWidth = barCount * (barWidth.toPx() + gapWidth.toPx()) - gapWidth.toPx()
        var startOffset = (size.width - animatedVolumeWidth) / 2 + (barWidth.toPx() / 2)

        val barMinHeight = 2.dp.toPx()
        val barMaxHeight = size.height / 2f
        val canvasCenterY = center.y

        repeat(barCount) { index ->
            val barHeightPercent = when (mode) {
                WaveformMode.REALTIME -> {
                    realtimeLevels.getOrNull(index) ?: 0f
                }
                WaveformMode.MINIMAL -> {
                    0f
                }
            }

            val barHeight = lerp(barMinHeight, barMaxHeight, barHeightPercent)

            drawLine(
                color = barColor,
                start = Offset(startOffset, canvasCenterY - barHeight / 2),
                end = Offset(startOffset, canvasCenterY + barHeight / 2),
                strokeWidth = barWidth.toPx(),
                cap = StrokeCap.Round,
            )
            startOffset += barWidth.toPx() + gapWidth.toPx()
        }
    }
}

@Preview
@Composable
fun WaveformComponentPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        WaveformComponent(
            mode = WaveformMode.MINIMAL,
        )
    }
}