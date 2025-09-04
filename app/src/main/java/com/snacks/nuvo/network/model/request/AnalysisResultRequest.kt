package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class AnalysisResultRequest(
    @SerializedName("callSession")
    val callSession: CallSessionRequest,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("endingScore")
    val endingScore: Int,
    @SerializedName("intonationScore")
    val intonationScore: Int,
    @SerializedName("pronunciationScore")
    val pronunciationScore: Int,
    @SerializedName("sentenceIndex")
    val sentenceIndex: Int,
    @SerializedName("speedScore")
    val speedScore: Int,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("recommendation")
    val recommendation: String? = null,
)