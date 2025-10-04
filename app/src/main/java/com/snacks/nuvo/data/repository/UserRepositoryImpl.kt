package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.UserDataSource
import com.snacks.nuvo.network.model.request.UserMissionRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override suspend fun getUserInfo(
    ) = userDataSource.getUserInfo()

    override suspend fun addMissionResult(
        userMissionRequest: UserMissionRequest
    ) = userDataSource.addMissionResult(userMissionRequest)

}