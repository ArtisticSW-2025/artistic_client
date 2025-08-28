package com.snacks.nuvo.ui.profile

data class ProfileUiState(
    val userName: String = "USER01!",
    val points: String = "13,847",
    val completedMissions: String = "12",
    val totalSpeakingTime: String = "1H 10M",
    val feedbackItems: List<FeedbackData> = listOf(
        FeedbackData("회의 일정 조율",
            content1 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content2 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content3 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다."
            ),
        FeedbackData("회의 일정 조율",
            content1 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content2 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content3 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다."
        ),
        FeedbackData("회의 일정 조율",
            content1 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content2 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content3 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다."
        ),
        FeedbackData("회의 일정 조율",
            content1 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content2 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다.",
            content3 = "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다."
        ),
    ),
    val isLoading: Boolean = false
)

data class FeedbackData(
    val title: String,
    val content1: String,
    val content2: String,
    val content3: String

)