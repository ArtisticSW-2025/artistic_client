package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.RankingResponse
import retrofit2.http.GET

interface RankingService {
    @GET("/rankings")
    suspend fun getRanking(): List<RankingResponse>
}