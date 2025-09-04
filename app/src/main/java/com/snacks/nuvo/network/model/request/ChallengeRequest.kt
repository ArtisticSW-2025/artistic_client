package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class ChallengeRequest(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("locked")
    val locked: Boolean,
    @SerializedName("stepNumber")
    val stepNumber: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
)