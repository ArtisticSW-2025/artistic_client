package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class ChallengeResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("stepNumber")
    val stepNumber: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("description")
    val description: String? = null,
)