package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class BadgeRequest(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null
)