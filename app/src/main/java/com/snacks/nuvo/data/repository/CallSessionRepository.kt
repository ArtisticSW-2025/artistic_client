package com.snacks.nuvo.data.repository

import com.snacks.nuvo.ui.home.RecommendScript

interface CallSessionRepository {

    suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean? = null,
        category: List<String>? = null,
    ): List<RecommendScript>

}