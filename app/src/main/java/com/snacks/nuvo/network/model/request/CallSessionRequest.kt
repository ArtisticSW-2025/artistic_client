package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class CallSessionRequest(
    @SerializedName("script")
    val script: ScriptRequest,
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: UserRequest,
    @SerializedName("analysisResult")
    val analysisResult: String? = null,
    @SerializedName("audioRecords")
    val audioRecords: String? = null,
    @SerializedName("endTime")
    val endTime: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("score")
    val score: Int? = null,
    @SerializedName("startTime")
    val startTime: String? = null,
)