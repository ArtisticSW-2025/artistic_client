package com.snacks.nuvo.ui.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme


@Composable
internal fun AuthButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit,
    label : String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 36.dp)
            .background(
                color = if (isEnabled) {
                    NuvoTheme.colors.mainGreen
                } else {
                    NuvoTheme.colors.mainGreen.copy(alpha = 0.6f)
                },
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(enabled = isEnabled) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            label,
            style = NuvoTheme.typography.interSemiBold15,
            color = NuvoTheme.colors.white
        )
    }
}
