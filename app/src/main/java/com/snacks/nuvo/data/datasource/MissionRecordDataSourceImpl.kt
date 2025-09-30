package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.di.IoDispatcher
import com.snacks.nuvo.network.model.response.MissionRecordResponse
import com.snacks.nuvo.network.service.MissionRecordService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MissionRecordDataSourceImpl @Inject constructor(
    private val missionRecordService: MissionRecordService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : MissionRecordDataSource {

    override suspend fun getTodayMission(): MissionRecordResponse {
        return withContext(ioDispatcher) {
            missionRecordService.getTodayMission()
        }
    }

}