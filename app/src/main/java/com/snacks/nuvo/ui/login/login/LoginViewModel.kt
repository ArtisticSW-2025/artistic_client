package com.snacks.nuvo.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TempAuthManager
import com.snacks.nuvo.network.model.request.LoginRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState.create())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateUsernameText(username: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            usernameText = username,
            isLoginButtonEnabled = username.isNotEmpty() && currentState.passwordText.isNotEmpty()
        )
    }

    fun updatePasswordText(password: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            passwordText = password,
            isLoginButtonEnabled = currentState.usernameText.isNotEmpty() && password.isNotEmpty()
        )
    }

    fun login(onSuccess: (String) -> Unit) {
        val currentState = _uiState.value
        if (currentState.isLoginButtonEnabled) {
            viewModelScope.launch {
                _uiState.value = currentState.copy(isLoading = true, error = null)

                val result = authRepository.login(
                    LoginRequest(
                        username = currentState.usernameText,
                        password = currentState.passwordText
                    )
                )

                result.onSuccess { response ->
                    TempAuthManager.issueAndSaveToken(response.accessToken)
                    _uiState.value = _uiState.value.copy(isLoading = false)
                    onSuccess(currentState.usernameText)
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
