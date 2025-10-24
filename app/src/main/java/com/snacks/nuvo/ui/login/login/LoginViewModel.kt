package com.snacks.nuvo.ui.login.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TempAuthManager
import com.snacks.nuvo.data.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import com.snacks.nuvo.network.model.request.LoginRequest
import com.snacks.nuvo.network.model.request.RegisterRequest
import com.snacks.nuvo.network.model.response.LoginResponse
import com.snacks.nuvo.network.model.response.RegisterResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
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
                    userPreferences.saveCredentials(
                        nickname = uiState.value.usernameText,
                        password = uiState.value.passwordText
                    )
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

class FakeAuthRepository : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return Result.success(
            LoginResponse(
                accessToken = "",
                tokenType = "",
            )
        )
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return Result.success(
            RegisterResponse(
                id = "",
                nickname = "",
                role = "",
                username = "",
                email = "",
                accessToken = "",
                tokenType = ""
            )
        )
    }
}