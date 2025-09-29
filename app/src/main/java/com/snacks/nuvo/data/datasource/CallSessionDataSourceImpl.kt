package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.di.IoDispatcher
import com.snacks.nuvo.network.model.response.CallSessionResponse
import com.snacks.nuvo.network.service.CallSessionService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CallSessionDataSourceImpl @Inject constructor(
    private val callSessionService: CallSessionService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : CallSessionDataSource {

    override suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean?,
        category: List<String>?,
    ): List<CallSessionResponse> {
        return withContext(ioDispatcher) {
            callSessionService.getRandomScripts(
                count = count,
                isBoolean = isBoolean,
                category = category
            )
        }
    }

}