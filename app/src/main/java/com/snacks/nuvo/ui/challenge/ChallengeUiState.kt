package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
data class ChallengeUiState (
    val isLoading: Boolean = false,
    val today: LocalDate? = LocalDate.now(),
    val weeklyMission: String = "",
    val phrase: String = "",
    val selectedNode: ChallengeNode? = null,
    val nodeList: List<ChallengeNode> = emptyList<ChallengeNode>(),

    val missionCount: Int = 0,

    val todayMissionTitle: String = "",
    val todayMissionDescription: String = "",
)

enum class NodeStatus { LOCKED, UNLOCKED, COMPLETED }

data class ChallengeNode(
    val id: Int? = null,
    val status: NodeStatus = NodeStatus.LOCKED,
)