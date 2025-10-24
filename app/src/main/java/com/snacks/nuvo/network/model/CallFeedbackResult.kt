package com.snacks.nuvo.network.model

data class CallFeedbackResult(
    val sentence_feedback: List<SentenceFeedback>,
    val total_feedback: TotalFeedback,
    val username: String
)

data class SentenceFeedback(
    val criteria: List<String>,
    val improved: String,
    val original: String
)

data class TotalFeedback(
    val accuracy: String,
    val politeness: String,
    val proactiveness: String,
    val total_score: Int,
    val error: String? = null
)