package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("role")
    val role: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("accessToken")
    val accessToken: String? = null,
    @SerializedName("tokenType")
    val tokenType: String? = null
)