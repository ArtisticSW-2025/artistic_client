package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName
import com.snacks.nuvo.network.model.SentenceFeedback
import com.snacks.nuvo.network.model.TotalFeedback

data class FeedbackResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("sentenceFeedback")
    val sentenceFeedback: List<SentenceFeedback>,

    @SerializedName("title")
    val title: String,

    @SerializedName("totalFeedback")
    val totalFeedback: TotalFeedback
)
