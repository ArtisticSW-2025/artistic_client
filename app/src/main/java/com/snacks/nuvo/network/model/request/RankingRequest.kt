package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class RankingRequest(
    @SerializedName("points")
    val points: Int,
    @SerializedName("rankPosition")
    val rankPosition: Int,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("userId")
    val userId: String,
)