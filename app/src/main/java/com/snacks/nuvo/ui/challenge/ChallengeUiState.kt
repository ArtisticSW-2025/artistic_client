package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class ChallengeUiState (
    val isLoading: Boolean = false,
    val today: LocalDate = LocalDate.now(),
    val weeklyMission: String = "",
    val phrase: String = "",
    val selectedNode: ChallengeNode? = null,
    val nodeList: List<ChallengeNode> = emptyList<ChallengeNode>(),
)

enum class NodeStatus { LOCKED, UNLOCKED, COMPLETED }

data class ChallengeNode(
    val id: Int? = null,
    val description: String = "",
    val date: LocalDate,
    val status: NodeStatus = NodeStatus.LOCKED,
)