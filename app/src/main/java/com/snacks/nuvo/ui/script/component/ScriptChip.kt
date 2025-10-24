package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Text
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptChip(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    label: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(
            1.dp,
            color = if (isSelected) NuvoTheme.colors.mainGreen else NuvoTheme.colors.gray3,
        ),
        color = if (isSelected) NuvoTheme.colors.mainGreen else NuvoTheme.colors.white,
        onClick = onClick
    ) {
        Box(
            modifier = Modifier
                // 기본 outline border 적용되나, 피그마는 inner boder여서 2dp씩 줄여 크기 동일하게 맞춤
                .padding(horizontal = 14.dp, vertical = 6.dp),
        ) {
            Text(
                label,
                style = if (isSelected) NuvoTheme.typography.pretendardBlack13 else NuvoTheme.typography.interRegular13,
                color = if (isSelected) NuvoTheme.colors.white else NuvoTheme.colors.gray4
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ScriptChipPreview() {
    ScriptChip(
        isSelected = true,
        label = "의료",
    ) {}
}

@Preview(showBackground = true)
@Composable
fun ScriptChipPreview2() {
    ScriptChip(
        isSelected = false,
        label = "서비스/쇼핑",
    ) {}
}