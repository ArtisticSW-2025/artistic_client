package com.snacks.nuvo.ui.profile.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.profile.FeedbackData
import com.snacks.nuvo.ui.theme.NuvoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedbackSection(
    feedbackItems: List<FeedbackData>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.gray2)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 26.dp)
        ) {
            FeedbackHeader()
            Spacer(modifier = Modifier.height(24.dp))
            FeedbackPager(feedbackItems = feedbackItems)
        }
    }
}

@Composable
private fun FeedbackHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(start = 20.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_chart_filled),
            tint = NuvoTheme.colors.subNavy,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "나의 피드백",
            style = NuvoTheme.typography.interBlack18,
            color = NuvoTheme.colors.subNavy
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FeedbackPager(feedbackItems: List<FeedbackData>) {
    val pagerState = rememberPagerState(pageCount = { feedbackItems.size })

    Column {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 16.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            FeedbackCard(
                feedback = feedbackItems[page]
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 페이지 인디케이터
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(feedbackItems.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    NuvoTheme.colors.mainGreen
                } else {
                    NuvoTheme.colors.gray3
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}

@Composable
private fun FeedbackCard(
    feedback: FeedbackData
) {
    Box(
        modifier = Modifier
            .width(324.dp)
            .height(261.dp)
            .background(
                color = NuvoTheme.colors.white,
                shape = RoundedCornerShape(15.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            FeedbackTitle(title = feedback.title)

            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.width(292.dp))

            Spacer(modifier = Modifier.height(12.dp))
            FeedbackItem(text = feedback.content)
            Spacer(modifier = Modifier.height(12.dp))
            FeedbackItem(text = feedback.content)
            Spacer(modifier = Modifier.height(12.dp))
            FeedbackItem(text = feedback.content)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun FeedbackTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = NuvoTheme.typography.interBlack15,
        color = NuvoTheme.colors.mainGreen,
        modifier = modifier
    )
}

@Composable
private fun FeedbackItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = NuvoTheme.colors.gray2,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(horizontal = 26.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            style = NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.subNavy,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}