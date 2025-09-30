package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.MissionRecordDataSource
import javax.inject.Inject

class MissionRecordRepositoryImpl @Inject constructor(
    private val missionRecordDataSource: MissionRecordDataSource,
) : MissionRecordRepository {

    override suspend fun getTodayMission(
    ) = missionRecordDataSource.getTodayMission()

}