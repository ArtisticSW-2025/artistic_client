package com.snacks.nuvo.ui.profile.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

// 재사용 가능한 정보 카드
@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = NuvoTheme.colors.white,
    shape: Shape = RoundedCornerShape(15.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                color = backgroundColor,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

// 그림자가 있는 카드
@Composable
fun ShadowCard(
    modifier: Modifier = Modifier,
    elevation: Dp = 16.dp,
    shape: Shape = RoundedCornerShape(15.dp),
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(elevation = elevation)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .background(
                color = NuvoTheme.colors.white,
                shape = shape
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

// 정보 행 (왼쪽 텍스트, 오른쪽 콘텐츠)
@Composable
fun InfoRow(
    leftText: String,
    leftTextStyle: TextStyle,
    leftTextColor: Color,
    rightText: String? = null,
    rightTextStyle: TextStyle? = null,
    rightTextColor: Color? = null,
    rightContent: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = leftText,
            style = leftTextStyle,
            color = leftTextColor
        )

        if (rightContent != null) {
            rightContent()
        } else if (rightText != null && rightTextStyle != null && rightTextColor != null) {
            Text(
                text = rightText,
                style = rightTextStyle,
                color = rightTextColor
            )
        }
    }
}