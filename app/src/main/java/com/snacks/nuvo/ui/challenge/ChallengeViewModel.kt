package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.MissionRecordRepository
import com.snacks.nuvo.data.repository.UserRepository
import com.snacks.nuvo.network.model.response.MissionRecordResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ChallengeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val missionRecordRepository: MissionRecordRepository,

) : ViewModel() {
    private val _uiState = MutableStateFlow(ChallengeUiState())
    val uiState: StateFlow<ChallengeUiState> = _uiState.asStateFlow()

    init {
        loadChallengeData()
    }

    fun loadChallengeData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val userInfo = userRepository.getUserInfo()
            val missionCount = userInfo.missionCount ?: 0

            val missionInfo = missionRecordRepository.getTodayMission()

            val nodeList = getNodeList(missionCount)

            _uiState.value = _uiState.value.copy(
                isLoading = false,
                phrase = "오늘의 한 마디, 내일의 자신감!",
                weeklyMission = "30분 이상 통화하기",
                missionCount = missionCount,
                todayMissionTitle = missionInfo.title,
                todayMissionDescription = missionInfo.description,
                nodeList = nodeList
            )
        }
    }

    private fun getNodeList(missionCount: Int): List<ChallengeNode> {
        val nodeList = mutableListOf<ChallengeNode>()
        nodeList.add(ChallengeNode(id = null))
        for (i in 1..missionCount + 10) {
            nodeList.add(
                ChallengeNode(
                    id = i,
                    status = when {
                        i <= missionCount -> NodeStatus.COMPLETED
                        i == missionCount + 1 -> NodeStatus.UNLOCKED
                        else -> NodeStatus.LOCKED
                    },
                )
            )
        }
        return nodeList
    }

    fun clearSelectedNode() {
        _uiState.value = _uiState.value.copy(selectedNode = null)
    }

    fun onNodeClicked(id: Int?) {
        val node = _uiState.value.nodeList.find { node -> node.id == id }
        if (node == null || node.status == NodeStatus.LOCKED || node.status == NodeStatus.COMPLETED) return

        _uiState.value = _uiState.value.copy(selectedNode = node)
    }
}

class FakeMissionRecordRepository : MissionRecordRepository {
    override suspend fun getTodayMission(): MissionRecordResponse {
        return MissionRecordResponse(
            description = "오늘의 미션 설명",
            id = 1,
            title = "오늘"
        )
    }
}