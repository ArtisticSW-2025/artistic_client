    package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.RankingDataSource
import com.snacks.nuvo.network.model.response.RankingResponse
import javax.inject.Inject

class RankingRepositoryImpl @Inject constructor(
    private val rankingDataSource: RankingDataSource
) : RankingRepository {
    override suspend fun getRanking(): List<RankingResponse> {
        return rankingDataSource.getRanking()
    }
}