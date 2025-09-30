package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.network.model.response.MissionRecordResponse

interface MissionRecordDataSource {

    suspend fun getTodayMission(): MissionRecordResponse

}