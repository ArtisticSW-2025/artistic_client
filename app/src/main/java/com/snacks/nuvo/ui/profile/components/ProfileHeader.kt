package com.snacks.nuvo.ui.profile.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    userName: String,
    onEditClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        ProfileImage()

        Spacer(modifier = Modifier.height(20.dp))

        ProfileName(name = userName)

        // 수정 버튼이 필요한 경우 주석 해제
        // EditProfileButton(onClick = onEditClick)

        Spacer(modifier = Modifier.height(34.dp))
    }
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    size: Dp = 100.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(NuvoTheme.colors.gray3)
    )
}

@Composable
private fun ProfileName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = NuvoTheme.typography.interBold24,
        color = NuvoTheme.colors.subNavy,
        modifier = modifier
    )
}