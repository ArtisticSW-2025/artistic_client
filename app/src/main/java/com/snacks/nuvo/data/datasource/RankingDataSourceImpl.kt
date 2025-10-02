package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.RankingResponse
import com.snacks.nuvo.network.service.RankingService
import javax.inject.Inject

class RankingDataSourceImpl @Inject constructor(
    private val rankingService: RankingService
) : RankingDataSource {
    override suspend fun getRanking(): List<RankingResponse> {
        return rankingService.getRanking()
    }
}