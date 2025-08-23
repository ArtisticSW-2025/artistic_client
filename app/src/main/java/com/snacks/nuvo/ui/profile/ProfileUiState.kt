package com.snacks.nuvo.ui.profile

data class ProfileUiState(
    val userName: String = "USER01!",
    val points: String = "13,847",
    val completedMissions: String = "12",
    val totalSpeakingTime: String = "1H 10M",
    val feedbackItems: List<FeedbackData> = listOf(
        FeedbackData("회의 일정 조율", "예의: 모든 참석자와의 질의응답을 통해 회의를 마무리하였고, 끝으로 상호 감사 인사를 전했습니다."),
        FeedbackData("프레젠테이션 스킬", "발표: 명확한 구조와 논리적 순서로 내용을 전달하였으며, 청중과의 아이컨택을 적극 활용했습니다."),
        FeedbackData("팀워크 협력", "소통: 팀원들의 의견을 경청하고 건설적인 피드백을 제공하여 원활한 협업 환경을 조성했습니다."),
        FeedbackData("문제 해결", "분석: 복잡한 상황을 체계적으로 분석하고 실용적인 해결방안을 제시했습니다.")
    ),
    val isLoading: Boolean = false
)

data class FeedbackData(
    val title: String,
    val content: String
)