package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.FeedbackResponse


interface FeedbackDataSource {
    suspend fun getFeedback(): List<FeedbackResponse>
}