package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class ChallengeProgressRequest(
    @SerializedName("challenge")
    val challenge: ChallengeRequest,
    @SerializedName("status")
    val status: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user")
    val user: UserRequest,
)