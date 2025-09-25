package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.CallSessionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CallSessionService {

    @GET("/call-sessions/random")
    suspend fun getRandomScripts(
        @Query("count") count: Int,
        @Query("is_call") isBoolean: Boolean? = null,
        @Query("category") category: List<String>? = null,
    ): List<CallSessionResponse>

}