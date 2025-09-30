package com.snacks.nuvo.data.repository

import com.snacks.nuvo.network.model.response.MissionRecordResponse

interface MissionRecordRepository {

    suspend fun getTodayMission(): MissionRecordResponse

}