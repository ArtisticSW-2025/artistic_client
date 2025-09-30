package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName
import java.util.Date

data class FeedbackResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("createdAt")
    val createdAt: String
)
