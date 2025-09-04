package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class MissionRequest(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("rewardPoints")
    val rewardPoints: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
)