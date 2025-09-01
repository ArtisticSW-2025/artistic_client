package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName
import com.snacks.nuvo.network.model.UserRole

data class UserResponse (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("username")
    val username: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("passwordHash")
    val passwordHash: String,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("role")
    val role: UserRole,
    @SerializedName("points")
    val points: Int,
    @SerializedName("rank")
    val rank: Int? = null,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)