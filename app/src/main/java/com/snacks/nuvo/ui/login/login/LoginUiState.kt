package com.snacks.nuvo.ui.login.login


data class LoginUiState(
    val usernameText: String = "",
    val passwordText: String = "",
    val isLoading: Boolean = false,
    val isLoginButtonEnabled: Boolean = false,
    val error: String? = null
) {
    companion object {
        fun create() = LoginUiState()
    }
}