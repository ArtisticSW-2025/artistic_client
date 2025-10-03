package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.request.UserMissionRequest
import com.snacks.nuvo.network.model.response.UserResponse

interface UserDataSource {

    suspend fun getUserInfo() : UserResponse

    suspend fun addMissionResult(userMissionRequest: UserMissionRequest) : UserResponse

}