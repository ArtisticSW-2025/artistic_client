package com.snacks.nuvo.ui.script.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.script.scriptdetail.Dialogue
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun ScriptContentCard(
    modifier: Modifier = Modifier,
    dialogues: List<Dialogue>,
) {
    ScriptDetailCard(
        modifier = modifier.padding(top = 28.dp, bottom = 32.dp, start = 32.dp, end = 32.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(32.dp),
                    color = NuvoTheme.colors.mainGreen,
                    shape = CircleShape,
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.ic_document_outlined),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = NuvoTheme.colors.white),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    "대본",
                    style = NuvoTheme.typography.interBlack15,
                    color = NuvoTheme.colors.gray5
                )
            }
            Spacer(Modifier.height(12.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = NuvoTheme.colors.gray4
            )
            Spacer(Modifier.height(24.dp))
            Column {
                dialogues.forEach { dialogue ->
                    Text(
                        "${dialogue.speaker}:\n${dialogue.content}",
                        style = NuvoTheme.typography.interRegular13.copy(lineHeight = 20.sp),
                        color = if (dialogue.speaker == "나") NuvoTheme.colors.black else NuvoTheme.colors.mainGreen
                    )
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ScriptContentCardPreview() {
    ScriptContentCard(
        dialogues = listOf(
            Dialogue(
                speaker = "병원 직원",
                content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
            ),
            Dialogue(
                speaker = "나",
                content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
            ),
            Dialogue(
                speaker = "병원 직원",
                content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
            ),
            Dialogue(
                speaker = "나",
                content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
            ),
            Dialogue(
                speaker = "병원 직원",
                content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
            ),
            Dialogue(
                speaker = "나",
                content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
            ),
            Dialogue(
                speaker = "병원 직원",
                content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
            ),
            Dialogue(
                speaker = "나",
                content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
            ),
            Dialogue(
                speaker = "병원 직원",
                content = "안녕하세요, 힐링내과입니다. 무엇을 도와드릴까요?"
            ),
            Dialogue(
                speaker = "나",
                content = "안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요. 안녕하세요. 초진 예약을 하고 싶어서 전화드렸어요."
            ),
        )
    )
}
