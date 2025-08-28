package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
internal fun ScriptListItem(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = Color.Transparent,
    ) {
        Box(modifier = Modifier.padding(end = 12.dp)) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .height(IntrinsicSize.Min)
            ) {
                Box(
                    modifier = Modifier
                        .width(8.dp)
                        .background(NuvoTheme.colors.subLightGreen)
                        .fillMaxHeight()
                )
                Spacer(Modifier.width(8.dp))
                Column {
                    Text(
                        title,
                        style = NuvoTheme.typography.interSemiBold14,
                        color = NuvoTheme.colors.subNavy
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        description,
                        style = NuvoTheme.typography.interMedium12,
                        color = NuvoTheme.colors.black
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterEnd),
                shadowElevation = 4.dp,
                color = NuvoTheme.colors.gray1,
                shape = CircleShape,
                onClick = onClick,
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(R.drawable.ic_calling_filled),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ScriptListItemPreview() {
    ScriptListItem(
        title = "병원 초진 예약 전화", description = "병원에 인사하고, 초진 예약을 할 때"
    ) {}
}
