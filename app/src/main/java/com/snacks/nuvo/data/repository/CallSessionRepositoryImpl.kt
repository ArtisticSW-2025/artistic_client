package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.CallSessionDataSource
import com.snacks.nuvo.ui.home.RecommendScript
import com.snacks.nuvo.ui.script.ScriptItem
import javax.inject.Inject

class CallSessionRepositoryImpl @Inject constructor(
    private val callSessionDataSource: CallSessionDataSource,
) : CallSessionRepository {

    override suspend fun getScripts(
        isCall: Boolean?,
        category: List<String>?,
        search: String?,
    ): List<ScriptItem> {
        val response = callSessionDataSource.getScripts(isCall, category, search)
        return response.map { item ->
            ScriptItem(
                id = item.id,
                title = item.name,
                description = item.mission
            )
        }
    }

    override suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean?,
        category: List<String>?,
    ): List<RecommendScript> {
        val response = callSessionDataSource.getRandomScripts(count, isBoolean, category)
        return response.map { item ->
            RecommendScript(
                id = item.id,
                title = item.name,
                description = item.mission
            )
        }
    }

    override suspend fun getScriptDetail(
        id: String,
    ) = callSessionDataSource.getScriptDetail(id)

}