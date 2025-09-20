package com.snacks.nuvo.ui.login.register

data class RegisterUiState(
    val nicknameText: String = "",
    val emailText: String = "",
    val passwordText: String = "",
    val confirmPasswordText: String = "",
    val isLoading: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val isPasswordLengthValid: Boolean = false,
    val isAllFieldsFilled: Boolean = false,
    val canRegister: Boolean = false,
    val showPasswordMismatchError: Boolean = false,
    val showPasswordLengthError: Boolean = false,
    val error: String? = null
) {
    companion object {
        fun create() = RegisterUiState()
    }
}