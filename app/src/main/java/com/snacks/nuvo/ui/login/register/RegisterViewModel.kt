package com.snacks.nuvo.ui.login.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TempAuthManager
import com.snacks.nuvo.network.model.request.RegisterRequest
import com.snacks.nuvo.ui.login.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState.create())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun updateNicknameText(nickname: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(nicknameText = nickname)
        updateValidationState()
    }

    fun updateEmailText(email: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(emailText = email)
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

        val isPasswordLengthValid = currentState.passwordText.length >= 8

        val isAllFieldsFilled = currentState.nicknameText.isNotEmpty() &&
                currentState.emailText.isNotEmpty() &&
                currentState.passwordText.isNotEmpty() &&
                currentState.confirmPasswordText.isNotEmpty()

        val canRegister = isAllFieldsFilled && isConfirmPasswordValid && isPasswordLengthValid

        val showPasswordMismatchError = currentState.confirmPasswordText.isNotEmpty() &&
                !isConfirmPasswordValid

        val showPasswordLengthError = currentState.passwordText.isNotEmpty() &&
                !isPasswordLengthValid

        _uiState.value = currentState.copy(
            isConfirmPasswordValid = isConfirmPasswordValid,
            isPasswordLengthValid = isPasswordLengthValid,
            isAllFieldsFilled = isAllFieldsFilled,
            canRegister = canRegister,
            showPasswordMismatchError = showPasswordMismatchError,
            showPasswordLengthError = showPasswordLengthError
        )
    }

    fun register(onSuccess: (String) -> Unit) {
        val currentState = _uiState.value
        if (currentState.canRegister) {
            viewModelScope.launch {
                _uiState.value = currentState.copy(isLoading = true, error = null)
                val result = authRepository.register(
                    RegisterRequest(
                        username = currentState.nicknameText,
                        email = currentState.emailText,
                        password = currentState.passwordText
                    )
                )

                result.onSuccess { response ->
                    TempAuthManager.issueAndSaveToken(response.accessToken)
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    onSuccess(currentState.nicknameText)
                }.onFailure {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = it.message ?: "An unknown error occurred"
                    )
                }
            }
        }
    }
}
