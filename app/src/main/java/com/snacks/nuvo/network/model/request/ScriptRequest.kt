package com.snacks.nuvo.network.model.request

import com.google.gson.annotations.SerializedName

data class ScriptRequest(
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
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
)