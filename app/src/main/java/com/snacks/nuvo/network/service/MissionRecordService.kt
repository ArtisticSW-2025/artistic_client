package com.snacks.nuvo.network.service

import com.snacks.nuvo.network.model.response.MissionRecordResponse
import retrofit2.http.GET

interface MissionRecordService {

    @GET("/mission-records/daily")
    suspend fun getTodayMission(): MissionRecordResponse

}