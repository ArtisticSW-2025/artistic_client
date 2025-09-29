package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.UserResponse
import retrofit2.http.GET

interface UserService {

    @GET("/users")
    suspend fun getUserInfo() : List<UserResponse>

}