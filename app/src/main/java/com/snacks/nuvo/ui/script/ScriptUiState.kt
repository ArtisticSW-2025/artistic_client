package com.snacks.nuvo.ui.script

data class ScriptUiState(
    val isLoading: Boolean = false,
    val selectedChipIndexes: Set<Int> = emptySet<Int>(),
    val isSmallTalkMode: Boolean = false,
    val isEmergencyMode: Boolean = false,
    val chipLabels: List<String> = ScriptLabel.entries.map { it.label },
    val scriptItems: List<ScriptItem> = emptyList<ScriptItem>(),
    val searchKeyword: String = ""
)

data class ScriptItem(
    val id: String?,
    val title: String,
    val description: String,
)

enum class ScriptLabel(
    val label: String,
) {
    MEDICAL("의료"),
    WORK("업무"),
    LIFESTYLE("생활"),
    SERVICE_SHOPPING("서비스/쇼핑"),
    EDUCATION("교육"),
    PERSONAL("개인"),
    PUBLIC_INSTITUTION("공공기관"),
    EMERGENCY("긴급/신고");
}