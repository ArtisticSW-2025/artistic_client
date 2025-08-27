package com.snacks.nuvo.ui.login.register

data class RegisterUiState(
    val nicknameText: String = "",
    val idText: String = "",
    val passwordText: String = "",
    val confirmPasswordText: String = "",
    val isLoading: Boolean = false,
    val isConfirmPasswordValid: Boolean = false,
    val isAllFieldsFilled: Boolean = false,
    val canRegister: Boolean = false,
    val showPasswordMismatchError: Boolean = false
) {
    companion object {
        fun create() = RegisterUiState()
    }
}