package com.snacks.nuvo.ui.call.retry

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.call.component.FloatingGifImage
import com.snacks.nuvo.ui.theme.NuvoTheme
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RetryScreen(
    appState: NuvoAppState,
    args: String,
    prevDestinationId: Int
) {
    LaunchedEffect(Unit) {
        delay(1500)

        val fullRoute = "${Routes.Call.ROUTE}?$args"

        appState.navController.navigate(fullRoute) {
            popUpTo(prevDestinationId) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        NuvoTheme.colors.lightGradient1,
                        NuvoTheme.colors.lightGradient2
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        FloatingGifImage(
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}