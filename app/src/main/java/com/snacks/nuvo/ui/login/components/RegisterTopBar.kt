package com.snacks.nuvo.ui.login.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RegisterTopBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(48.dp)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "뒤로가기",
                tint = NuvoTheme.colors.gray6
            )
        }

        Text(
            text = "회원가입",
            style = NuvoTheme.typography.interSemiBold20,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}