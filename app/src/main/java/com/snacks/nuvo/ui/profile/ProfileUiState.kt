package com.snacks.nuvo.ui.profile

import com.snacks.nuvo.network.model.SentenceFeedback
import com.snacks.nuvo.network.model.TotalFeedback

data class ProfileUiState(
    val userName: String = "",
    val points: String = "0",
    val completedMissions: String = "00",
    val totalSpeakingTime: String = "0H 00M",
    val feedbackItems: List<FeedbackData> = emptyList(),
    val isLoading: Boolean = false
)

data class FeedbackData(
    val id: String,
    val sentenceFeedback: SentenceFeedback,
    val totalFeedback: TotalFeedback,
)
