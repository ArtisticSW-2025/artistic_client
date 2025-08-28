package com.snacks.nuvo.ui.login.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoginFooter(
    modifier: Modifier = Modifier,
    onFindIdClick: () -> Unit = {},
    onFindPasswordClick: () -> Unit = {},
    onRegisterClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            31.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        Text(
            "아이디 찾기",
            style = NuvoTheme.typography.interRegular12,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.clickable { onFindIdClick() }
        )
        Text(
            "비밀번호 찾기",
            style = NuvoTheme.typography.interRegular12,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.clickable { onFindPasswordClick() }
        )
        Text(
            "회원가입",
            style = NuvoTheme.typography.interRegular12,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.clickable { onRegisterClick() }
        )
    }
}