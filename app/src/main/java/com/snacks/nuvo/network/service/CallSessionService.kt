package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.CallSessionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CallSessionService {

    @GET("/call-sessions")
    suspend fun getScripts(
        @Query("is_call") isCall: Boolean? = null,
        @Query("category") category: List<String>? = null,
        @Query("search") search: String? = null,
    ): List<CallSessionResponse>

    @GET("/call-sessions/random")
    suspend fun getRandomScripts(
        @Query("count") count: Int,
        @Query("is_call") isCall: Boolean? = null,
        @Query("category") category: List<String>? = null,
    ): List<CallSessionResponse>

    @GET("/call-sessions/{id}")
    suspend fun getScriptDetail(
        @Path(value = "id") id: String,
    ): CallSessionResponse

}