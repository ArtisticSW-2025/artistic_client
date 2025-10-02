package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.UserResponse

interface UserDataSource {

    suspend fun getUserInfo() : UserResponse

}