package com.snacks.nuvo.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.TempAuthManager
import com.snacks.nuvo.data.preferences.UserPreferences
import com.snacks.nuvo.network.model.request.LoginRequest
import com.snacks.nuvo.ui.login.login.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _loginStatus = MutableStateFlow<LoginStatus>(LoginStatus.Loading)
    val loginStatus: StateFlow<LoginStatus> = _loginStatus.asStateFlow()

    val savedNickname: String? = userPreferences.getNickname()

    init {
        tryAutoLogin()
    }

    private fun tryAutoLogin() {
        viewModelScope.launch {

            val nickname = userPreferences.getNickname()
            val password = userPreferences.getPassword()

            if (nickname != null && password != null) {
                authRepository.login(LoginRequest(username = nickname, password = password))
                    .onSuccess { response ->
                        TempAuthManager.issueAndSaveToken(response.accessToken)
                        _loginStatus.value = LoginStatus.Success
                    }
                    .onFailure {
                        userPreferences.clearCredentials()
                        _loginStatus.value = LoginStatus.Failure
                    }
            } else {
                _loginStatus.value = LoginStatus.Failure
            }
        }
    }
}

sealed class LoginStatus {
    object Loading : LoginStatus()
    object Success : LoginStatus()
    object Failure : LoginStatus()
}