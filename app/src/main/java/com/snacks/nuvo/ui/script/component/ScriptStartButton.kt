package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptStartButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .width(210.dp)
            .height(48.dp),
        color = NuvoTheme.colors.white,
        shadowElevation = 4.dp,
        shape = RoundedCornerShape(27.dp),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                label,
                style = NuvoTheme.typography.interBold22,
                color = NuvoTheme.colors.mainGreen,
            )
        }
    }
}

@Preview
@Composable
fun ScriptStartButtonPreview() {
    ScriptStartButton(
        label = "START",
        onClick = {}
    )
}