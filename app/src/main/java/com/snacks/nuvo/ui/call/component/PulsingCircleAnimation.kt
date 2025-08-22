package com.snacks.nuvo.ui.call.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun PulsingCircleAnimation(
    modifier: Modifier = Modifier,
    color: Color = NuvoTheme.colors.white,
    numberOfWaves: Int = 5,
    animationDuration: Int = 3000
) {
    val infiniteTransition = rememberInfiniteTransition()

    val waveProgress = List(numberOfWaves) { index ->
        val progress by infiniteTransition.animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    durationMillis = animationDuration,
                    delayMillis = index * (animationDuration / numberOfWaves)
                ),
                repeatMode = RepeatMode.Restart
            )
        )
        progress
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = this.center
            val maxRadius = size.minDimension / 2f

            waveProgress.forEach { progress ->
                val currentRadius = maxRadius * progress
                val currentAlpha = -2.5f * progress + 2.5f

                drawCircle(
                    color = color.copy(alpha = currentAlpha),
                    radius = currentRadius,
                    center = center,
                    style = Stroke(width = (8.dp * currentAlpha).toPx())
                )
            }
        }

        Box (
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(color = NuvoTheme.colors.white),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_calling_filled),
                contentDescription = null,
                colorFilter = ColorFilter.tint(color = NuvoTheme.colors.mainGreen),
                modifier = Modifier.size(60.dp)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PulsingCircleAnimationPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PulsingCircleAnimation(
            modifier = Modifier.size(150.dp)
        )
    }
}