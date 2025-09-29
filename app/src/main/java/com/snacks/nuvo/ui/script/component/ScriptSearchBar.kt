package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptSearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color.Transparent
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = keyword,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    textStyle = NuvoTheme.typography.interRegular13.copy(color = NuvoTheme.colors.gray5),
                    cursorBrush = SolidColor(NuvoTheme.colors.gray5),
                    decorationBox = { innerTextField ->
                        if (keyword.isEmpty()) {
                            Text(
                                text = "검색어를 입력하세요",
                                style = NuvoTheme.typography.interRegular13.copy(color = NuvoTheme.colors.gray4)
                            )
                        }
                        innerTextField()
                    }
                )
                Spacer(Modifier.width(4.dp))
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = NuvoTheme.colors.gray4),
                    modifier = Modifier.size(14.dp)
                        .clickable(
                            onClick = onSearch,
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }
            Spacer(Modifier.height(8.dp))
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), color = NuvoTheme.colors.gray3
            ) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ScriptSearchBarPreview() {
    ScriptSearchBar(
        keyword = "sisi",
        onValueChange = {},
        onSearch = {}
    )
}