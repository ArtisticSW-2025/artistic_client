package com.snacks.nuvo.ui.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.CircularProgressIndicator
import androidx.wear.compose.material3.Text
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.data.preferences.UserPreferences
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.login.components.AuthButton
import com.snacks.nuvo.ui.login.components.LoginFooter
import com.snacks.nuvo.ui.login.components.LoginForm
import com.snacks.nuvo.ui.login.components.LoginHeader
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoginScreen(
    appState: NuvoAppState,
    viewModel: LoginViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.white),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.weight(169f))

            LoginHeader()

            Spacer(Modifier.height(48.dp))

            LoginForm(
                usernameText = uiState.usernameText,
                passwordText = uiState.passwordText,
                onUsernameTextChange = viewModel::updateUsernameText,
                onPasswordTextChange = viewModel::updatePasswordText,
                onLoginClick = {
                    viewModel.login { nickname ->
                        appState.navigate("${Routes.Login.WELCOME}/$nickname")
                    }
                }
            )

            Spacer(Modifier.height(10.dp))

            if (uiState.error != null) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 43.dp),
                    text = "아이디 또는 비밀번호가 잘못 되었습니다.\n아이디와 비밀번호를 정확히 입력해주세요.",
                    style = NuvoTheme.typography.pretendardRegular12,
                    lineHeight = 20.sp,
                    color = Color(0xFFFF0E0E),
                )
            }

            Spacer(Modifier.height(20.dp))

            AuthButton(
                isEnabled = uiState.isLoginButtonEnabled,
                onClick = {
                    viewModel.login { nickname ->
                        appState.navigate("${Routes.Login.WELCOME}/$nickname")
                    }
                },
                label = "로그인"
            )

            Spacer(Modifier.height(20.dp))

            LoginFooter(
                onFindIdClick = {
                    // 아이디 찾기 로직
                },
                onFindPasswordClick = {
                    // 비밀번호 찾기 로직
                },
                onRegisterClick = {
                    appState.navigate(Routes.Login.REGISTER)
                }
            )

            Spacer(Modifier.weight(260f))
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val context = LocalContext.current
    val viewModel = remember { LoginViewModel(
        authRepository = FakeAuthRepository(),
        userPreferences = UserPreferences(context)
    ) }
    LoginScreen(
        appState = rememberNuvoAppState(),
        viewModel = viewModel
    )
}