package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptDetailCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = NuvoTheme.colors.white,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            content()
        }
    }
}