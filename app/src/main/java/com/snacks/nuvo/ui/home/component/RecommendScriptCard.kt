package com.snacks.nuvo.ui.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .padding(horizontal = 16.dp, vertical = 24.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    // 네모박스
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .fillMaxHeight()
                            .background(color = NuvoTheme.colors.mainGreen)
                    )
                    // 글씨들
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .weight(1f)
                    ) {
                        Text(
                            title,
                            style = NuvoTheme.typography.interBlack20,
                            color = NuvoTheme.colors.subNavy,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            description,
                            style = NuvoTheme.typography.interMedium12.copy(lineHeight = 15.sp),
                            color = NuvoTheme.colors.gray5,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    // 이미지
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
internal fun PreviewReccommentScriptCard() {
    RecommendScriptCard(
        modifier = Modifier,
        title = "안녕",
        description = "안녕안녕안녕"
    ) { }
}

@Preview
@Composable
internal fun PreviewRecommentScriptCard2() {
    RecommendScriptCard(
        modifier = Modifier,
        title = "안녕",
        description = "안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕"
    ) { }
}

@Preview
@Composable
internal fun PreviewRecommentScriptCard3() {
    RecommendScriptCard(
        modifier = Modifier,
        title = "안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕",
        description = "안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕안녕"
    ) { }
}