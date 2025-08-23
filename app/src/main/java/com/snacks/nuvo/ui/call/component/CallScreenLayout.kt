package com.snacks.nuvo.ui.call.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun CallScreenLayout(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    prevName: String,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Button(
            modifier = Modifier
                .zIndex(1f)
                .padding(top = 12.dp, start = 28.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = NuvoTheme.colors.white,
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = onNavigateBack
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = prevName,
                style = NuvoTheme.typography.interBlack15.copy(color = NuvoTheme.colors.white),
            )
            Spacer(Modifier.width(8.dp))
        }

        content()
    }
}