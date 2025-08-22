package com.snacks.nuvo.ui.script.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Text
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptToggleButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .background(
                brush = if (isSelected) Brush.verticalGradient(
                    colors = listOf(
                        NuvoTheme.colors.lightGradient1,
                        NuvoTheme.colors.lightGradient2
                    )
                ) else SolidColor(NuvoTheme.colors.gray3)
            )
            .clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = label,
                tint = NuvoTheme.colors.white,
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(
                label,
                style = NuvoTheme.typography.interMedium13,
                color = NuvoTheme.colors.white
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ScriptToggleButtonPreview() {
    ScriptToggleButton(
        icon = R.drawable.ic_flag_outlined,
        label = "스몰토크 모드",
        isSelected = true
    ) {}
}

@Preview(showBackground = true)
@Composable
internal fun ScriptToggleButtonPreview2() {
    ScriptToggleButton(
        icon = R.drawable.ic_flag_outlined,
        label = "스몰토크 모드",
        isSelected = false
    ) {}
}