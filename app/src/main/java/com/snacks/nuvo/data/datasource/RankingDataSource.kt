package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.RankingResponse

interface RankingDataSource {
    suspend fun getRanking(): List<RankingResponse>
}