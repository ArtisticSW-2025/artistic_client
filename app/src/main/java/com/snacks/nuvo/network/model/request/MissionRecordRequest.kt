package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class MissionRecordRequest(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("mission")
    val mission: MissionRequest,
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: UserRequest,
    @SerializedName("completedAt")
    val completedAt: String? = null,
    @SerializedName("id")
    val id: String? = null,
)