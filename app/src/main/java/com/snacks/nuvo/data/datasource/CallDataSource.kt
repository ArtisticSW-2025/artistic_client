package com.snacks.nuvo.data.datasource

import android.content.Context
import com.snacks.nuvo.network.model.SpeechResult
import kotlinx.coroutines.flow.Flow

interface CallDataSource {
    fun startListening(context: Context): Flow<SpeechResult>
    fun stopListening()
    fun connectWebSocket(user: String, sessionId: String): Flow<String>
    fun disconnectWebSocket()
    fun sendUserMessage(message: String)
}