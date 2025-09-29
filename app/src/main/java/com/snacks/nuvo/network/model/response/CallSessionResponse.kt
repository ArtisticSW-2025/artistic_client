package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName
import com.snacks.nuvo.ui.script.scriptdetail.Dialogue

data class CallSessionResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("categories")
    val categories: List<String>,
    @SerializedName("isCall")
    val isCall: Boolean,
    @SerializedName("mission")
    val mission: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("prompts")
    val prompts: String,
    @SerializedName("purpose")
    val purpose: String,
    @SerializedName("script")
    val script: List<Dialogue>
)