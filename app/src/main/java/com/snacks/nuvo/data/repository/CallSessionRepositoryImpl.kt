package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.CallSessionDataSource
import javax.inject.Inject

class CallSessionRepositoryImpl @Inject constructor(
    private val callSessionDataSource: CallSessionDataSource,
) : CallSessionRepository {

    override suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean?,
        category: List<String>?,
    ) = callSessionDataSource.getRandomScripts(count, isBoolean, category)

}