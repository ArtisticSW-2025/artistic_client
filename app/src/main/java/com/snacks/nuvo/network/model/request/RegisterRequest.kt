package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String? = null,
)