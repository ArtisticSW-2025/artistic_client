package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("points")
    val points: Int,

    @SerializedName("rank")
    val rank: Int? = null,

    @SerializedName("missionCount")
    val missionCount: Int? = null,

    @SerializedName("totalCallDuration")
    val totalCallDuration: Int? = null,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)
