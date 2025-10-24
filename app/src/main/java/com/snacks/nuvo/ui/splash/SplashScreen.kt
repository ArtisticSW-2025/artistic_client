package com.snacks.nuvo.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.components.HiGifImage
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
fun SplashScreen(
    appState: NuvoAppState,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val loginStatus by viewModel.loginStatus.collectAsState()
    val nickname = viewModel.savedNickname

    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            LoginStatus.Success -> {
                // 성공 시 메인 그래프로 이동
                nickname?.let { savedName ->
                    appState.navController.navigate("${Routes.Login.WELCOME}/$savedName") {
                        popUpTo(Routes.Login.SPLASH) { inclusive = true }
                    }
                }
            }
            LoginStatus.Failure -> {
                // 실패 시 로그인 화면으로 이동
                appState.navController.navigate(Routes.Login.LOGIN) {
                    popUpTo(Routes.Login.SPLASH) { inclusive = true }
                }
            }
            LoginStatus.Loading -> {
                // 로딩 중에는 UI만 표시
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
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
                Spacer(Modifier.weight(350f))
            }
        }
    }
}