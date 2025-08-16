package com.snacks.nuvo.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.component.NoRippleInteractionSource
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RecommendScriptCard(
    modifier: Modifier,
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .height(130.dp)
            .width(344.dp)
            .clickable(
                interactionSource = remember { NoRippleInteractionSource() },
                indication = null
            ) { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = NuvoTheme.colors.white)
        ) {
            Box(
                modifier = Modifier
                    .background(color = NuvoTheme.colors.white)
                    .padding(start = 16.dp, end = 20.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .height(70.dp)
                            .background(color = NuvoTheme.colors.mainGreen)
                    )
                    Spacer(Modifier.width(20.dp))
                    Column {
                        Text(
                            title,
                            style = NuvoTheme.typography.interBlack20.copy(color = NuvoTheme.colors.subNavy)
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            description,
                            style = NuvoTheme.typography.interMedium12.copy(color = NuvoTheme.colors.gray5)
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .background(
                                color = NuvoTheme.colors.gray1,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_calling_filled),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
internal fun PreviewREcommentScriptCard() {
    RecommendScriptCard(
        modifier = Modifier,
        title = "안녕",
        description = "안녕안녕안녕"
    ) { }
}