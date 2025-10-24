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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RegisterTopBar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_back,),
            contentDescription = "뒤로가기",
            tint = NuvoTheme.colors.black
        )

        Text(
            text = "회원가입",
            style = NuvoTheme.typography.pretendardBlack15,
            color = NuvoTheme.colors.black,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}