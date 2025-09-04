package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class BadgeResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("condition")
    val condition: String,
    @SerializedName("createdAt")
    val createdAt: String,
)