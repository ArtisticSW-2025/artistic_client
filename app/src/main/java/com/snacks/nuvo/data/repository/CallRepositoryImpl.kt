package com.snacks.nuvo.data.repository;

import com.snacks.nuvo.data.datasource.CallDataSource;
import com.snacks.nuvo.network.model.SpeechResult;
import android.content.Context;
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class CallRepositoryImpl @Inject
constructor(
        private val callDataSource:CallDataSource
) : CallRepository {

    override suspend fun startListening(context:Context): Flow<SpeechResult> =
    callDataSource.startListening(context)

    override suspend fun stopListening() = callDataSource.stopListening()

    override suspend fun connectWebSocket(user: String, sessionId: String): Flow<String> =
    callDataSource.connectWebSocket(user, sessionId)

    override suspend fun disconnectWebSocket() = callDataSource.disconnectWebSocket()

    override suspend fun sendUserMessage(message: String) = callDataSource.sendUserMessage(message)
}