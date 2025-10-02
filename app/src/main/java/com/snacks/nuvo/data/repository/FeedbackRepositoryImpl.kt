package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.FeedbackDataSource
import com.snacks.nuvo.ui.profile.FeedbackData
import javax.inject.Inject

class FeedbackRepositoryImpl @Inject constructor(
    private val feedbackDatasource: FeedbackDataSource
) : FeedbackRepository {
    override suspend fun getFeedback(): List<FeedbackData> {
        val response = feedbackDatasource.getFeedback()
        return response.map {
            FeedbackData(
                id = it.id,
                userId = it.userId,
                content = it.content,
                createdAt = it.createdAt
            )
        }
    }
}