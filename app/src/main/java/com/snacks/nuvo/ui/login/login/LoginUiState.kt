package com.snacks.nuvo.ui.login.login


data class LoginUiState(
    val idText: String = "",
    val passwordText: String = "",
    val isLoading: Boolean = false,
    val isLoginButtonEnabled: Boolean = false
) {
    companion object {
        fun create() = LoginUiState()
    }
}