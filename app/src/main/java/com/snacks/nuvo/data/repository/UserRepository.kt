package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.response.UserResponse

interface UserRepository {

    suspend fun getUserInfo() : List<UserResponse>

}