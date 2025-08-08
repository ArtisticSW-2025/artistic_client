package com.snacks.nuvo.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object NuvoTheme {
    val colors: NuvoColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: NuvoTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}
