package com.snacks.nuvo.ui.script.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptIconToggleButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier.size(38.dp),
            color = if (isSelected) NuvoTheme.colors.white else Color.Transparent,
            shape = CircleShape,
            border = BorderStroke(
                1.dp,
                color = NuvoTheme.colors.white,
            ),
            onClick = onClick,
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = if (isSelected) NuvoTheme.colors.mainGreen else NuvoTheme.colors.white),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        Text(
            if (isSelected) "ON" else "OFF",
            style = if (isSelected) NuvoTheme.typography.interBlack13 else NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.white
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScriptIconToggleButtonPreview() {
    ScriptIconToggleButton(
        icon = R.drawable.ic_users_outlined,
        isSelected = true,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ScriptIconToggleButtonPreview2() {
    ScriptIconToggleButton(
        icon = R.drawable.ic_users_outlined,
        isSelected = false,
        onClick = {}
    )
}