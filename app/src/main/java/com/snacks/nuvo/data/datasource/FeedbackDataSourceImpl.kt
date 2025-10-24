package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.di.IoDispatcher
import com.snacks.nuvo.network.model.response.FeedbackResponse
import com.snacks.nuvo.network.service.FeedbackService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FeedbackDataSourceImpl @Inject constructor(
    private val feedbackService: FeedbackService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : FeedbackDataSource {

    override suspend fun getFeedback(): List<FeedbackResponse> {
        return withContext(ioDispatcher) {
            feedbackService.getFeedback()
        }
    }
}