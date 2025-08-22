package com.snacks.nuvo.ui.script

data class ScriptUiState (
    val isLoading: Boolean = false,
    val selectedChipIndexes: Set<Int> = emptySet<Int>(),
    val isSmallTalkMode: Boolean = false,
    val isEmergencyMode: Boolean=false,
    val chipLabels: List<String> = listOf<String>("의료", "업무", "생활", "서비스/쇼핑", "교육", "개인", "공공기관", "긴급/신고"),
    val scriptItems: List<ScriptItem> = emptyList<ScriptItem>()
)

data class ScriptItem(
    val id: Int,
    val title: String,
    val description:  String
)
