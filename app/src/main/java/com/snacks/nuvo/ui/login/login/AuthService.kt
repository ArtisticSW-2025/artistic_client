package com.snacks.nuvo.ui.login.login

import com.snacks.nuvo.network.model.request.LoginRequest
import com.snacks.nuvo.network.model.request.RegisterRequest
import com.snacks.nuvo.network.model.response.LoginResponse
import com.snacks.nuvo.network.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}
