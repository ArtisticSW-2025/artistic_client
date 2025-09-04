package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class UserBadgeResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("user")
    val user: UserResponse,
    @SerializedName("badge")
    val badge: BadgeResponse,
    @SerializedName("awardedAt")
    val awardedAt: String,
)