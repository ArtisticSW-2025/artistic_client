package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.response.CallSessionResponse

interface CallSessionRepository {

    suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean? = null,
        category: List<String>? = null,
    ): List<CallSessionResponse>

}