package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class AnalysisResultResponse(
    @SerializedName("callSession")
    val callSession: CallSessionResponse,
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
    val recommendation: String? = null
)