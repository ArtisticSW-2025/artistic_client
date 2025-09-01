package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("rankPosition")
    val rankPosition: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("updatedAt")
    val updatedAt: String
)