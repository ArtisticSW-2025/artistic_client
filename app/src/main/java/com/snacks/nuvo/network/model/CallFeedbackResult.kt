package com.snacks.nuvo.network.model

data class CallFeedbackResult(
    val score: Int = 0,
    val feedback: List<String> = emptyList<String>()
)
