package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.request.UserMissionRequest
import com.snacks.nuvo.network.model.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @GET("/users/-")
    suspend fun getUserInfo() : UserResponse

    @POST("/users/-/mission")
    suspend fun addMissionResult(@Body userMissionRequest: UserMissionRequest) : UserResponse

}