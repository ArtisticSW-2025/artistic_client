package com.snacks.nuvo.ui.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.login.LoginScreen
import com.snacks.nuvo.ui.login.login.LoginViewModel
import com.snacks.nuvo.ui.login.register.RegisterScreen
import com.snacks.nuvo.ui.login.register.RegisterViewModel

fun NavGraphBuilder.loginGraph(
    appState: NuvoAppState
) {
    composable(
        route = Routes.Login.LOGIN
    ) {
        val loginViewModel: LoginViewModel = hiltViewModel()
        LoginScreen(
            appState = appState,
            viewModel = loginViewModel
        )
    }

    composable(
        route = Routes.Login.REGISTER
    ) {
        val registerViewmodel: RegisterViewModel = hiltViewModel()
        RegisterScreen(
            appState = appState,
            onBackClick = {
                appState.navController.popBackStack()
            },
            viewModel = registerViewmodel
        )
    }

    composable(
        route = Routes.Login.WELCOME
    ) {
        WelcomScreen(
            appState = appState
        )
    }
}