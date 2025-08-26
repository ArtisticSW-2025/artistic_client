package com.snacks.nuvo.ui.login.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState.create())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateIdText(id: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            idText = id,
            isLoginButtonEnabled = id.isNotEmpty() && currentState.passwordText.isNotEmpty()
        )
    }

    fun updatePasswordText(password: String) {
        val currentState = _uiState.value
        _uiState.value = currentState.copy(
            passwordText = password,
            isLoginButtonEnabled = currentState.idText.isNotEmpty() && password.isNotEmpty()
        )
    }

    fun login(onSuccess: () -> Unit) {
        val currentState = _uiState.value
        if (currentState.isLoginButtonEnabled) {
            _uiState.value = currentState.copy(isLoading = true)

            // 실제 로그인 로직을 여기에 구현
            // 예시: 로그인 성공 시
            _uiState.value = currentState.copy(isLoading = false)
            onSuccess()
        }
    }
}