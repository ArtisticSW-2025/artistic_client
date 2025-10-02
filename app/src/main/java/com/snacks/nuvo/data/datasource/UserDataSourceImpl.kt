package com.snacks.nuvo.data.datasource

import com.snacks.nuvo.di.IoDispatcher
import com.snacks.nuvo.network.model.response.UserResponse
import com.snacks.nuvo.network.service.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): UserDataSource {

    override suspend fun getUserInfo(): UserResponse {
        return withContext(ioDispatcher) {
            userService.getUserInfo()
        }
    }

}