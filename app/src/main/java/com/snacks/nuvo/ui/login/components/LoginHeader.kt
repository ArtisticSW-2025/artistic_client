package com.snacks.nuvo.ui.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoginHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(166.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_login_logo_filled),
            contentDescription = "Login Logo",
            modifier = Modifier
                .width(213.dp)
                .height(137.dp)
        )

        Spacer(Modifier.height(15.dp))

        Text(
            "NUVO",
            style = NuvoTheme.typography.interBlack32,
            color = NuvoTheme.colors.mainGreen
        )

        Spacer(Modifier.height(8.dp))

        Text(
            "당신의 첫 통화 파트너",
            style = NuvoTheme.typography.interSemiBold20,
            color = NuvoTheme.colors.gray6
        )
    }
}