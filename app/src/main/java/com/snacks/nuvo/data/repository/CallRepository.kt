package com.snacks.nuvo.data.repository;

import android.content.Context;
import com.snacks.nuvo.network.model.SpeechResult;
import kotlinx.coroutines.flow.Flow

interface CallRepository {
    suspend fun startListening(context:Context): Flow<SpeechResult>
    suspend fun stopListening()
    suspend fun connectWebSocket(user: String, sessionId: String): Flow<String>
    suspend fun disconnectWebSocket()
    suspend fun sendUserMessage(message: String)
}