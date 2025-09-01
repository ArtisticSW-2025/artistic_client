package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class ChallengeProgressResponse(
    @SerializedName("challenge")
    val challenge: ChallengeResponse,
    @SerializedName("status")
    val status: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("completedAt")
    val completedAt: String? = null
)