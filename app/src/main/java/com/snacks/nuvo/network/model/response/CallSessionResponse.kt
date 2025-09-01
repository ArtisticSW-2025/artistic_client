package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class CallSessionResponse(
    @SerializedName("script")
    val script: ScriptResponse,
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("startTime")
    val startTime: String? = null,
    @SerializedName("endTime")
    val endTime: String? = null,
    @SerializedName("audioRecords")
    val audioRecords: String? = null,
    @SerializedName("analysisResult")
    val analysisResult: String? = null,
    @SerializedName("score")
    val score: Int? = null
)