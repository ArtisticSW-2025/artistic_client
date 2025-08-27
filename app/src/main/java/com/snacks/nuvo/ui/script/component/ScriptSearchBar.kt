package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
internal fun ScriptSearchBar(
    modifier: Modifier = Modifier,
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
                Text(
                    "검색어를 입력하세요",
                    style = NuvoTheme.typography.interRegular13,
                    color = NuvoTheme.colors.gray4
                )
                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = NuvoTheme.colors.gray4),
                    modifier = Modifier.size(14.dp)
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
    ScriptSearchBar()
}