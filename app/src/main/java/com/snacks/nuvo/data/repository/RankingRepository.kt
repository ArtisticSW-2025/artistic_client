package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.response.RankingResponse

interface RankingRepository {
    suspend fun getRanking(): List<RankingResponse>
}