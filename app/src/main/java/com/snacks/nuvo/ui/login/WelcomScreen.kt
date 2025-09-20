package com.snacks.nuvo.ui.login

import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.theme.Inter
import com.snacks.nuvo.ui.theme.NuvoTheme
import kotlinx.coroutines.delay

@Composable
internal fun WelcomScreen(appState: NuvoAppState? = null, nickname: String ) {
    LaunchedEffect(Unit) {
        delay(3000) // 3초 대기
        appState?.navigate(Routes.Home.ROUTE) // 원하는 라우트로 이동
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
            Spacer(Modifier.height(264.dp))

            Image(
                painter = painterResource(id = R.drawable.ic_login_logo_filled),
                contentDescription = "Login Logo",
                modifier = Modifier
                    .width(285.dp)
                    .height(183.dp)
                    .alpha(0.4f) ,
            )
            Spacer(Modifier.height(47.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        fontSize = 28.sp,
                        letterSpacing = 0.sp,
                        color = NuvoTheme.colors.white
                    )
                    ){
                        append(nickname)
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    ) ){
                        append("님,")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.Black,
                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    )
                    ){
                        append("\nNUVO")
                    }
                    withStyle(style = SpanStyle(
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        color = NuvoTheme.colors.white
                    ) ){
                        append("에 오신걸 환영해요!")
                    }
                },
                textAlign = TextAlign.Center,
                lineHeight = 28.sp

            )
            }
    }
}

@Preview
@Composable
fun a (){

    WelcomScreen(nickname = "도도도")
}
