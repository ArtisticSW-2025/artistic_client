package com.snacks.nuvo.data.repository

import com.snacks.nuvo.ui.profile.FeedbackData

interface FeedbackRepository {
    suspend fun getFeedback(): List<FeedbackData>
}