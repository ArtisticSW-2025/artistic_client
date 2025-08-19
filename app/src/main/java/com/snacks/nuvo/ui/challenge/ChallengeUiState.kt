package com.snacks.nuvo.ui.challenge

import java.time.LocalDate

data class ChallengeUiState(
    val isLoading: Boolean = false,
    val weeklyMission: String = "",
    val selectedNode: ChallengeNode? = null,
    val nodeList: List<ChallengeNode> = emptyList<ChallengeNode>(),
)

enum class NodeStatus { LOCKED, UNLOCKED, COMPLETED }

data class ChallengeNode(
    val id: Int? = null,
    val phrase: String = "",
    val description: String = "",
    val date: LocalDate,
    val status: NodeStatus = NodeStatus.LOCKED,
)