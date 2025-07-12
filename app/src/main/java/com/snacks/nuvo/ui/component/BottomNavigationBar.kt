package com.snacks.nuvo.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.R

@Composable
fun BottomNavigationBar(
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .shadow(
                elevation = 12.dp,
                spotColor = Color(0X1F000000),
                ambientColor = Color(0X1F000000)
            ),
        shape = RoundedCornerShape(
            topStart = 76.dp, topEnd = 76.dp, bottomStart = 76.dp, bottomEnd = 76.dp
        ),
        color = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.4f to Color(0XFF32757E).copy(alpha = 0.8f),
                            1f to Color(0XFF53A790).copy(alpha = 0.8f)
                        )
                    )
                )
                .padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                content()
            }
        }
    }
}

@Composable
fun BottomNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    @DrawableRes selectedIcon: Int,
    @DrawableRes unselectedIcon: Int,
    label: String,
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
        color = Color.Transparent,
        interactionSource = NoRippleInteractionSource()
    ) {
        Box(
            modifier.padding(vertical = 8.dp, horizontal = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = if (selected) selectedIcon else unselectedIcon),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    label,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Preview()
@Composable
fun BottomNaviagtionBarPreview() {
    BottomNavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        BottomNavigationBarItem(
            selected = false,
            label = "홈",
            onClick = {},
            selectedIcon = R.drawable.ic_home_filled,
            unselectedIcon = R.drawable.ic_home_outlined
        )
        BottomNavigationBarItem(
            selected = true,
            label = "스크립트",
            onClick = {},
            selectedIcon = R.drawable.ic_document_filled,
            unselectedIcon = R.drawable.ic_document_outlined
        )
        BottomNavigationBarItem(
            selected = false,
            label = "랭킹",
            onClick = {},
            selectedIcon = R.drawable.ic_flag_filled,
            unselectedIcon = R.drawable.ic_flag_outlined
        )
        BottomNavigationBarItem(
            selected = false,
            label = "챌린지",
            onClick = {},
            selectedIcon = R.drawable.ic_star_filled,
            unselectedIcon = R.drawable.ic_star_outlined
        )
        BottomNavigationBarItem(
            selected = false,
            label = "프로필",
            onClick = {},
            selectedIcon = R.drawable.ic_user_filled,
            unselectedIcon = R.drawable.ic_user_outlined
        )

    }
}