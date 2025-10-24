package com.snacks.nuvo.ui.profile.components

import android.R.attr.maxHeight
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.R
import com.snacks.nuvo.network.model.TotalFeedback
import com.snacks.nuvo.ui.component.ExpandableTextCard
import com.snacks.nuvo.ui.profile.FeedbackData
import com.snacks.nuvo.ui.theme.NuvoTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeedbackSection(
    modifier: Modifier = Modifier,
    feedbackItems: List<FeedbackData>,
    onClick: (FeedbackData) -> Unit,
) {
    Box(
        modifier = modifier
            .background(color = NuvoTheme.colors.gray2),
    ) {
        if (feedbackItems.isEmpty()) {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "피드백이 없습니다",
                    style = NuvoTheme.typography.interMedium15,
                    color = NuvoTheme.colors.subNavy
                )
            }
        } else {
            Column {
                Spacer(modifier = Modifier.height(25.dp))
                FeedbackHeader(modifier = Modifier.padding(start = 40.dp))
                Spacer(modifier = Modifier.height(11.dp))
                FeedbackPager(
                    feedbackItems = feedbackItems,
                    onClick = onClick
                )
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun FeedbackHeader(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_chart_filled),
            tint = NuvoTheme.colors.subNavy,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "나의 피드백",
            style = NuvoTheme.typography.pretendardBlack18,
            color = NuvoTheme.colors.subNavy
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FeedbackPager(
    modifier: Modifier = Modifier,
    feedbackItems: List<FeedbackData>,
    onClick: (FeedbackData) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { feedbackItems.size })

    HorizontalPager(
        modifier = modifier.fillMaxWidth(),
        state = pagerState,
        pageSpacing = 20.dp,
        contentPadding = PaddingValues(horizontal = 45.dp),
        verticalAlignment = Alignment.Top
    ) { page ->
        FeedbackCard(
            feedback = feedbackItems[page],
            onClick = onClick
        )
    }
}

@Composable
private fun FeedbackCard(
    modifier: Modifier = Modifier,
    feedback: FeedbackData,
    onClick: (FeedbackData) -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = NuvoTheme.colors.white,
                shape = RoundedCornerShape(15.dp)
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            FeedbackTitle(title = feedback.title)

            Spacer(modifier = Modifier.height(10.dp))

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp))

            Spacer(modifier = Modifier.height(20.dp))
            FeedbackItem(text = feedback.totalFeedback.accuracy)
            Spacer(modifier = Modifier.height(10.dp))
            FeedbackItem(text = feedback.totalFeedback.politeness)
            Spacer(modifier = Modifier.height(10.dp))
            FeedbackItem(text = feedback.totalFeedback.proactiveness)
            Spacer(modifier = Modifier.height(24.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_list_magnifying_glass_filled),
                contentDescription = null,
                modifier = Modifier
                    .clickable(onClick = { onClick(feedback) }),
            )
        }

    }
}

@Composable
private fun FeedbackTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Text(
        text = title,
        style = NuvoTheme.typography.pretendardBlack15,
        lineHeight = 21.sp,
        color = NuvoTheme.colors.mainGreen,
        modifier = modifier
    )
}

@Composable
private fun FeedbackItem(
    modifier: Modifier = Modifier,
    text: String,
    padding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        ExpandableTextCard(
            text = text,
            color = NuvoTheme.colors.gray2,
            textStyle = NuvoTheme.typography.interMedium13
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeedbackSectionPreview() {
    FeedbackSection(
        feedbackItems = listOf(
            FeedbackData(
                id = "",
                title = "회의 일정 조율",
                sentenceFeedbacks = listOf(),
                totalFeedback = TotalFeedback(
                    accuracy = "정보 제공이 정확하고 간결합니다....",
                    politeness ="존댓말과 배려 표현이 부족합니다...",
                    proactiveness = "요청이 명확하고 구체적입니다... 이렇게하면안못생김 요청이 명확하고 구체적입니다... 요청이 명확하고 구체적입니다...",
                    total_score = 1
                )
            ),
            FeedbackData(
                id = "",
                title = "회의 일정 조율",
                sentenceFeedbacks = listOf(),
                totalFeedback = TotalFeedback(
                    accuracy = "정보 제공이 정확하고 간결합니다....",
                    politeness ="존댓말과 배려 표현이 부족합니다...",
                    proactiveness = "요청이 명확하고 구체적입니다... 이렇게하면안못생김 요청이 명확하고 구체적입니다... 요청이 명확하고 구체적입니다...",
                    total_score = 1
                )
            ),
        ),
        onClick = { }
    )
}