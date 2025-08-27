package com.snacks.nuvo.ui.challenge.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.snacks.nuvo.util.dropShadow
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun DatePhraseCard(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    date: LocalDate?,
    phrase: String,
) {
    Card (
        modifier = modifier
            .height(height = 70.dp)
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                ),
                offsetX = 0.dp,
                offsetY = 10.dp,
                blur = 9.dp,
                spread = 0.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(
            bottomEnd = 20.dp,
            bottomStart = 20.dp
        ),
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .hazeEffect(
                        state = hazeState,
                        style = HazeStyle(
                            blurRadius = 20.dp,
                            tint = null,
                            noiseFactor = 0f,
                        )
                    )
                    .background(color = NuvoTheme.colors.white.copy(alpha = 0.9f)),
                contentAlignment = Alignment.Center,
            ) { }

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = date?.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) ?: "",
                        style = NuvoTheme.typography.interBold20.copy(color = NuvoTheme.colors.subNavy)
                    )
                    Spacer(Modifier.height(1.dp))
                    Text(
                        text = phrase,
                        style = NuvoTheme.typography.interSemiBold13.copy(color = NuvoTheme.colors.lightGradient2)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
internal fun PreviewDatePhraseCard() {
    DatePhraseCard(
        modifier = Modifier,
        hazeState = HazeState(),
        date = LocalDate.now(),
        phrase = "오늘의 한 마디, 내일의 자신감!",
    )
}