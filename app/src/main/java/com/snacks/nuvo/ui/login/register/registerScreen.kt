package com.snacks.nuvo.ui.login.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.CircularProgressIndicator
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.data.preferences.UserPreferences
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.login.components.AuthButton
import com.snacks.nuvo.ui.login.components.RegisterForm
import com.snacks.nuvo.ui.login.components.RegisterTopBar
import com.snacks.nuvo.ui.login.login.FakeAuthRepository
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
                modifier = Modifier
                    .clickable(onClick = onBackClick)
            )

            Spacer(Modifier.height(36.dp))

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


@Preview
@Composable
fun RegisterScreenPreview() {
    val context = LocalContext.current
    val viewModel = remember { RegisterViewModel(
        authRepository = FakeAuthRepository(),
        userPreferences = UserPreferences(context)
    ) }
    RegisterScreen(
        appState = rememberNuvoAppState(),
        viewModel = viewModel
    )
}