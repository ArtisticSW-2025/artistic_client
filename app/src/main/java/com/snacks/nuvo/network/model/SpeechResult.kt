package com.snacks.nuvo.network.model

data class SpeechResult(
    val transcribedText: String = "",
    val volume: Float = 0f,
    val isFinal: Boolean = false
)