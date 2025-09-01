package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class UserBadgeRequest(
    @SerializedName("awardedAt")
    val awardedAt: String,
    @SerializedName("badge")
    val badge: BadgeRequest,
    @SerializedName("user")
    val user: UserRequest,
    @SerializedName("id")
    val id: String? = null
)