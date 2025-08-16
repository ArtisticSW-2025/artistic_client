package com.snacks.nuvo.ui.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoadingIndicator() {
    CircularProgressIndicator(
        color = NuvoTheme.colors.mainGreen
    )
}
