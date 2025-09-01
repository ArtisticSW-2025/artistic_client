package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName
import com.snacks.nuvo.network.model.UserRole

data class UserRequest(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("passwordHash")
    val passwordHash: String,
    @SerializedName("points")
    val points: Int,
    @SerializedName("role")
    val role: UserRole,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("rank")
    val rank: Int? = null
)