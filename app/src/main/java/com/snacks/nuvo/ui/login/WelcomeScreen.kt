package com.snacks.nuvo.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.components.HiGifImage
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.ui.theme.Pretendard
import kotlinx.coroutines.delay

@Composable
internal fun WelcomeScreen(appState: NuvoAppState? = null, nickname: String ) {
    LaunchedEffect(Unit) {
        delay(3000) // 3초 대기
        appState?.navController?.navigate(Routes.Home.ROUTE) {
            popUpTo(Routes.Login.ROUTE) { inclusive = true }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        NuvoTheme.colors.lightGradient1,
                        NuvoTheme.colors.lightGradient2
                    )
                )
            ),
        contentAlignment = Alignment.TopCenter

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(Modifier.weight(270f))

            HiGifImage(
                modifier = Modifier
                    .width(350.dp),
            )
            Spacer(Modifier.height(47.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Black,
                        fontSize = 28.sp,
                        letterSpacing = 0.sp,
                        color = NuvoTheme.colors.white
                    )
                    ){
                        append(nickname)
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.SemiBold,

                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    )
                    ){
                        append("님,")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Black,
                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    )
                    ){
                        append("\nNUVO")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    ) ){
                        append("에 오신걸 환영해요!")
                    }
                },
                textAlign = TextAlign.Center,
                lineHeight = 33.sp

            )
            Spacer(Modifier.weight(350f))
        }
    }
}

@Preview
@Composable
fun a (){

    WelcomeScreen(nickname = "도도도")
}
