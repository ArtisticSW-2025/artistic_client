package com.snacks.nuvo.data.repository

import com.snacks.nuvo.data.datasource.UserDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override suspend fun getUserInfo(
    ) = userDataSource.getUserInfo()

}