package com.snacks.nuvo.ui.script.scriptdetail

data class ScriptDetailUiState(
    val isLoading: Boolean = false,
    val id: Int? = null,
    val isSmallTalkMode: Boolean = false,
    val isEmergencyMode: Boolean = false,
    val title: String = "",
    val mission: String = "",
    val goal: String = "",
    val dialogues: List<Dialogue> = emptyList<Dialogue>(),
)

data class Dialogue(
    val speaker: String,
    val content: String,
)