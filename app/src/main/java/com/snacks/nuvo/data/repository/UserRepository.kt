package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.request.UserMissionRequest
import com.snacks.nuvo.network.model.response.UserResponse

interface UserRepository {

    suspend fun getUserInfo() : UserResponse

    suspend fun addMissionResult(userMissionRequest: UserMissionRequest) : UserResponse

}