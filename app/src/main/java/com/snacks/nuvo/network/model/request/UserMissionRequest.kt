package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName
import com.snacks.nuvo.network.model.UserRole

data class UserMissionRequest(
    @SerializedName("callDuration")
    val callDuration: Int,
    @SerializedName("feedback")
    val feedback: String,
    @SerializedName("score")
    val score: Int,
)