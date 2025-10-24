package com.snacks.nuvo.ui.login

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.login.login.LoginScreen
import com.snacks.nuvo.ui.login.login.LoginViewModel
import com.snacks.nuvo.ui.login.register.RegisterScreen
import com.snacks.nuvo.ui.login.register.RegisterViewModel
import com.snacks.nuvo.ui.splash.SplashScreen

fun NavGraphBuilder.authGraph(
    appState: NuvoAppState
) {
    navigation(
        startDestination = Routes.Login.SPLASH,
        route = Routes.Login.ROUTE
    ) {
        composable(
            Routes.Login.SPLASH
        ) {
            SplashScreen(appState = appState)
        }

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
            route = "${Routes.Login.WELCOME}/{nickname}",
            arguments = listOf(navArgument("nickname") { type = NavType.StringType })
        ) {
            val nickname = it.arguments?.getString("nickname") ?: ""
            WelcomeScreen(
                appState = appState,
                nickname = nickname
            )
        }
    }
}