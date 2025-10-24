package com.snacks.nuvo.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun ExpandableTextCard(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = NuvoTheme.colors.white,
    textStyle: TextStyle
) {
    var expanded: Boolean by remember { mutableStateOf(false) }
    var lastIndex: Int by remember { mutableIntStateOf(0) }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(size = 20.dp),
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(horizontal = 26.dp, vertical = 10.dp)
        ) {
            Text(
                text = text,
                maxLines = 1,
                overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    lastIndex = textLayoutResult.getLineEnd(
                        lineIndex = 0,
                        visibleEnd = true
                    )
                },
                style = textStyle.copy(lineHeight = TextUnit.Unspecified)
            )

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Text(
                    text = text.substring(startIndex = lastIndex),
                    maxLines = Int.MAX_VALUE,
                    style = textStyle.copy(lineHeight = TextUnit.Unspecified)
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpandableTextCardPreview() {
    ExpandableTextCard(
        modifier = Modifier
            .padding(top = 50.dp),
        text = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
        textStyle = NuvoTheme.typography.pretendardSemiBold14.copy(color = NuvoTheme.colors.mainGreen)
    )
}