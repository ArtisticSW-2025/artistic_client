package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class MissionResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rewardPoints")
    val rewardPoints: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("description")
    val description: String? = null,
)