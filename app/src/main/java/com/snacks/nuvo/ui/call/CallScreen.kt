package com.snacks.nuvo.ui.call

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.snacks.nuvo.NuvoAppState

@Composable
internal fun CallScreen(appState: NuvoAppState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { appState.navController.popBackStack() }
        ) {
            Text("뒤로")
        }
    }
}