package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class MissionRecordResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
)