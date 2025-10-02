package com.snacks.nuvo.network.model.response

import com.google.gson.annotations.SerializedName

data class RankingResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("missionCount")
    val missionCount: Int,
    @SerializedName("missionRecordCount")
    val missionRecordCount: Int,
    @SerializedName("missionTotalSeconds")
    val missionTotalSeconds: Int,
    @SerializedName("points")
    val points: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("totalCallDuration")
    val totalCallDuration: Int,
    @SerializedName("username")
    val username: String
)
