package com.snacks.nuvo.ui.call.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
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
                feedbackItems = listOf(
                    SentenceFeedbackItems("예약하고 싶어요", "예의 능동성 정확성", "예의 능동성 정확성"),
                    SentenceFeedbackItems("두 번째 문장", "예의 능동성 정확성", "예의 능동성 정확성"),
                    SentenceFeedbackItems("세 번째 문장", "예의 능동성 정확성", "예의 능동성 정확성")
                )
            )
        }
    }
}

@Composable
fun FeedbackSheet(
    feedbackItems: List<SentenceFeedbackItems>,
    onClose: () -> Unit = {}
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .background(NuvoTheme.colors.white)
            .padding(horizontal = 36.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(45.dp))

        // 헤더
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                "Sentence Feedback",
                style = NuvoTheme.typography.interBlack16,
                color = NuvoTheme.colors.mainGreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.height(55.dp))

        // Feedback 리스트
        feedbackItems.forEachIndexed { index, item ->
            FeedbackRow(item)
            if (index != feedbackItems.lastIndex) {
                Spacer(modifier = Modifier.height(28.dp))
                Divider(color = NuvoTheme.colors.gray4, thickness = 1.dp)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}


data class SentenceFeedbackItems(
    val original: String,
    val criteria: String,
    val improved: String
)

@Composable
fun FeedbackRow(item: SentenceFeedbackItems) {
    Text(
        item.original,
        style = NuvoTheme.typography.interMedium13,
        color = NuvoTheme.colors.gray4
    )
    Spacer(modifier = Modifier.height(18.dp))

    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_flag_filled),
            tint = NuvoTheme.colors.mainGreen,
            contentDescription = "Score flag"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            item.criteria,
            style = NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.mainGreen
        )
        Spacer(modifier = Modifier.height(6.dp))
    }

    Spacer(modifier = Modifier.height(4.dp))

    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_flag_filled),
            tint = NuvoTheme.colors.subNavy,
            contentDescription = "Score flag"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            item.improved,
            style = NuvoTheme.typography.interMedium13,
            color = NuvoTheme.colors.subNavy
        )
    }
}