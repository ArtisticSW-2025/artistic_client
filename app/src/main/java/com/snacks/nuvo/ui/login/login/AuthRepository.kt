package com.snacks.nuvo.ui.login.login

import com.snacks.nuvo.network.model.request.LoginRequest
import com.snacks.nuvo.network.model.request.RegisterRequest
import com.snacks.nuvo.network.model.response.LoginResponse
import com.snacks.nuvo.network.model.response.RegisterResponse
import javax.inject.Inject

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
    suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse>
}

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = authService.login(loginRequest)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(registerRequest: RegisterRequest): Result<RegisterResponse> {
        return try {
            val response = authService.register(registerRequest)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
