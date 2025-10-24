package com.snacks.nuvo.ui.call.result

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.network.model.SentenceFeedback
import com.snacks.nuvo.ui.profile.FeedbackRow
import com.snacks.nuvo.ui.theme.NUVOTheme
import com.snacks.nuvo.ui.theme.NuvoTheme

@Preview(showBackground = true)
@Composable
fun FeedbackSheetPreview() {
    NUVOTheme { // 테마 적용
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(NuvoTheme.colors.white)
        ) {
            FeedbackSheet(
                sentenceFeedbacks = listOf(
                    SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
                    SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
                    SentenceFeedback(listOf("예의", "능동성", "정확성"),"죄송합니다, 비뇨기과의 예약을 진행하고 싶은데요", "예약하고 싶어요"),
                ),
            )
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun FeedbackSheet(
    modifier: Modifier = Modifier,
    sentenceFeedbacks: List<SentenceFeedback>
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
                "Sentence Feedback",
                style = NuvoTheme.typography.interBlack16,
                color = NuvoTheme.colors.mainGreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(55.dp))

        sentenceFeedbacks.forEachIndexed { index, item ->
            FeedbackRow(item)
            Spacer(modifier = Modifier.height(28.dp))
            Divider(color = NuvoTheme.colors.gray4, thickness = 1.dp)
            Spacer(modifier = Modifier.height(24.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}