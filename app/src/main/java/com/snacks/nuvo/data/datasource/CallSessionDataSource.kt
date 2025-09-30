package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.CallSessionResponse

interface CallSessionDataSource {

    suspend fun getScripts(
        isCall: Boolean?,
        category: List<String>?,
        search: String?
    ): List<CallSessionResponse>

    suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean?,
        category: List<String>?,
    ): List<CallSessionResponse>

    suspend fun getScriptDetail(
        id: String
    ): CallSessionResponse

}