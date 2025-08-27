package com.snacks.nuvo.ui.login.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState.create())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateNicknameText(nickname: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(nicknameText = nickname)
        updateValidationState()
    }

    fun updateIdText(id: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(idText = id)
        updateValidationState()
    }

    fun updatePasswordText(password: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(passwordText = password)
        updateValidationState()
    }

    fun updateConfirmPasswordText(confirmPassword: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(confirmPasswordText = confirmPassword)
        updateValidationState()
    }

    private fun updateValidationState() {
        val currentState = _uiState.value

        val isConfirmPasswordValid = currentState.confirmPasswordText == currentState.passwordText &&
                currentState.confirmPasswordText.isNotEmpty()

        val isAllFieldsFilled = currentState.nicknameText.isNotEmpty() &&
                currentState.idText.isNotEmpty() &&
                currentState.passwordText.isNotEmpty() &&
                currentState.confirmPasswordText.isNotEmpty()

        val canRegister = isAllFieldsFilled && isConfirmPasswordValid

        val showPasswordMismatchError = currentState.confirmPasswordText.isNotEmpty() &&
                !isConfirmPasswordValid

        _uiState.value = currentState.copy(
            isConfirmPasswordValid = isConfirmPasswordValid,
            isAllFieldsFilled = isAllFieldsFilled,
            canRegister = canRegister,
            showPasswordMismatchError = showPasswordMismatchError
        )
    }

    fun register(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.canRegister) {
            _uiState.value = currentState.copy(isLoading = true)

            // 실제 회원가입 로직을 여기에 구현
            // 예시: 회원가입 성공 시
            _uiState.value = currentState.copy(isLoading = false)
            onSuccess()
        }
    }
}