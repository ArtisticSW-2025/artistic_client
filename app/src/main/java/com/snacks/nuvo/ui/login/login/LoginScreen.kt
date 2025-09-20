package com.snacks.nuvo.ui.login.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoginHeader()

            Spacer(Modifier.height(48.dp))

            LoginForm(
                usernameText = uiState.usernameText,
                passwordText = uiState.passwordText,
                onUsernameTextChange = viewModel::updateUsernameText,
                onPasswordTextChange = viewModel::updatePasswordText
            )

            Spacer(Modifier.height(30.dp))

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
        }
    }
}