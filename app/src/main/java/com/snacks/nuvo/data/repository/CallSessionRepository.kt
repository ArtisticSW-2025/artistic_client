package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.response.CallSessionResponse
import com.snacks.nuvo.ui.home.RecommendScript
import com.snacks.nuvo.ui.script.ScriptItem

interface CallSessionRepository {

    suspend fun getScripts(
        isCall: Boolean? = null,
        category: List<String>? = null,
        search: String? = null
    ): List<ScriptItem>

    suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean? = null,
        category: List<String>? = null,
    ): List<RecommendScript>

    suspend fun getScriptDetail(
        id: String
    ): CallSessionResponse

}