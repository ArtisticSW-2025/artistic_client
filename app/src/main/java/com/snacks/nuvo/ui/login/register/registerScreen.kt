package com.snacks.nuvo.ui.login.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.components.RegisterButton
import com.snacks.nuvo.ui.login.components.RegisterForm
import com.snacks.nuvo.ui.login.components.RegisterTopBar
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun RegisterScreen(
    appState: NuvoAppState,
    onBackClick: () -> Unit = {},
    viewModel: RegisterViewModel = RegisterViewModel()
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
                idText = uiState.idText,
                passwordText = uiState.passwordText,
                confirmPasswordText = uiState.confirmPasswordText,
                onNicknameChange = viewModel::updateNicknameText,
                onIdChange = viewModel::updateIdText,
                onPasswordChange = viewModel::updatePasswordText,
                onConfirmPasswordChange = viewModel::updateConfirmPasswordText,
                showPasswordMismatchError = uiState.showPasswordMismatchError,
            )

            Spacer(Modifier.height(60.dp))

            RegisterButton(
                isEnabled = uiState.canRegister,
                onClick = {
                    viewModel.register {
                        appState.navigate(Routes.Login.WELCOME)
                    }
                }
            )

        }
    }
}