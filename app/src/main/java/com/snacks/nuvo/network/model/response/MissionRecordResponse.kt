package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class MissionRecordResponse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("mission")
    val mission: MissionResponse,
    @SerializedName("status")
    val status: String,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("completedAt")
    val completedAt: String? = null,
)