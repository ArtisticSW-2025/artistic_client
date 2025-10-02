package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.FeedbackResponse
import retrofit2.http.GET

interface FeedbackService {

    @GET("/feedbacks")
    suspend fun getFeedback(): List<FeedbackResponse>
}