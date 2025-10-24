package com.snacks.nuvo.ui.script.scriptdetail

data class ScriptDetailUiState(
    val isLoading: Boolean = false,
    val id: String? = null,
    val isSmallTalkMode: Boolean = false,
    val isEmergencyMode: Boolean = false,
    val title: String = "",
    val mission: String = "",
    val goal: String = "",
    val dialogues: List<Dialogue> = emptyList<Dialogue>(),
    val contactName: String = "",
)

data class Dialogue(
    val speaker: String,
    val content: String,
)