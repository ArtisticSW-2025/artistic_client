package com.snacks.nuvo.ui.login.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.CircularProgressIndicator
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.components.AuthButton
import com.snacks.nuvo.ui.login.components.RegisterForm
import com.snacks.nuvo.ui.login.components.RegisterTopBar
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RegisterScreen(
    appState: NuvoAppState,
    onBackClick: () -> Unit = {},
    viewModel: RegisterViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = NuvoTheme.colors.white),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            RegisterTopBar(
                onBackClick = onBackClick
            )

            RegisterForm(
                nicknameText = uiState.nicknameText,
                emailText = uiState.emailText,
                passwordText = uiState.passwordText,
                confirmPasswordText = uiState.confirmPasswordText,
                onNicknameChange = viewModel::updateNicknameText,
                onEmailChange = viewModel::updateEmailText,
                onPasswordChange = viewModel::updatePasswordText,
                onConfirmPasswordChange = viewModel::updateConfirmPasswordText,
                showPasswordMismatchError = uiState.showPasswordMismatchError,
                showPasswordLengthError = uiState.showPasswordLengthError,
            )

            Spacer(Modifier.height(60.dp))

            AuthButton(
                isEnabled = uiState.canRegister,
                onClick = {
                    viewModel.register {
                            nickname ->
                        appState.navigate("${Routes.Login.WELCOME}/$nickname")
                    }
                },
                label = "회원가입"
            )

        }
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}