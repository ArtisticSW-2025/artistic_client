package com.snacks.nuvo.ui.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.R
import com.snacks.nuvo.network.model.SentenceFeedback
import com.snacks.nuvo.network.model.TotalFeedback
import com.snacks.nuvo.ui.theme.NuvoTheme

@Preview(showBackground = true)
@Composable
fun FeedbackCardSheetPreview() {
    FeedbackCardSheet(
        feedbackData = FeedbackData(
            id = "",
            title = "회의 일정 조율",
            sentenceFeedbacks = listOf(
                SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
                SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
                SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
            ),
            totalFeedback = TotalFeedback(
                accuracy = "정보 제공이 정확하고 간결합니다....",
                politeness ="존댓말과 배려 표현이 부족합니다...",
                proactiveness = "요청이 명확하고 구체적입니다... 이렇게하면안못생김 요청이 명확하고 구체적입니다... 요청이 명확하고 구체적입니다...",
                total_score = 1
            )
        )
    )
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun FeedbackCardSheet(
    modifier: Modifier = Modifier,
    feedbackData: FeedbackData
) {
    val scrollState = rememberScrollState()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(0.dp, screenHeight * 0.8f)
            .verticalScroll(scrollState)
            .padding(horizontal = 36.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Overall Feedback",
                style = NuvoTheme.typography.interBlack16,
                lineHeight = 22.sp,
                color = NuvoTheme.colors.mainGreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        FeedbackItem(text = feedbackData.totalFeedback.accuracy,)
        Spacer(modifier = Modifier.height(12.dp))
        FeedbackItem(text = feedbackData.totalFeedback.politeness)
        Spacer(modifier = Modifier.height(12.dp))
        FeedbackItem(text = feedbackData.totalFeedback.proactiveness)
        Spacer(modifier = Modifier.height(60.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Sentence Feedback",
                style = NuvoTheme.typography.interBlack16,
                color = NuvoTheme.colors.mainGreen,
                modifier = Modifier.align(Alignment.Center),
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        feedbackData.sentenceFeedbacks.forEachIndexed { index, item ->
            FeedbackRow(item)
            Spacer(modifier = Modifier.height(28.dp))
            Divider(color = NuvoTheme.colors.gray4, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun FeedbackItem(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
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
            lineHeight = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun FeedbackRow(item: SentenceFeedback) {
    Text(
        item.original,
        style = NuvoTheme.typography.interMedium13,
        color = NuvoTheme.colors.gray4
    )
    Spacer(modifier = Modifier.height(18.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_smiley_filled),
            tint = NuvoTheme.colors.mainGreen,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.criteria.joinToString(" "),
            style = NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.mainGreen
        )
        Spacer(modifier = Modifier.height(6.dp))
    }

    Spacer(modifier = Modifier.height(4.dp))

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_play_filled),
            tint = NuvoTheme.colors.subNavy,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            item.improved,
            style = NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.subNavy,
            lineHeight = 18.sp
        )
    }
}