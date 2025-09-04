package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class ScriptResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("level")
    val level: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("description")
    val description: String? = null,
)