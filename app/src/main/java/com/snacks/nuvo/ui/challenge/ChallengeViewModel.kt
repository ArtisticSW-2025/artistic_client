package com.snacks.nuvo.ui.challenge

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class ChallengeViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(ChallengeUiState())
    val uiState: StateFlow<ChallengeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getPhrase()
        getWeeklyMission()
        getDailyNodeList()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getPhrase() {
        _uiState.value = _uiState.value.copy(phrase = "오늘의 한 마디, 내일의 자신감!")
    }

    private fun getWeeklyMission() {
        _uiState.value = _uiState.value.copy(weeklyMission = "30분 이상 통화하기")
    }

    private fun getDailyNodeList() {
        val firstDayOfMonth = _uiState.value.today.withDayOfMonth(1)
        val lastDayOfMonth = _uiState.value.today.with(TemporalAdjusters.lastDayOfMonth())

        val nodeList = mutableListOf<ChallengeNode>()
        var currentDate = firstDayOfMonth

        nodeList.add(
            ChallengeNode(
                id = null,
                date = currentDate,
            )
        )
        while (!currentDate.isAfter(lastDayOfMonth)) {
            nodeList.add(
                ChallengeNode(
                    id = currentDate.dayOfMonth,
                    description = "오늘(${currentDate.dayOfMonth}일) 하루를 요약해서 말해보자",
                    date = currentDate,
                    status = when {
                        currentDate.isAfter(_uiState.value.today) -> NodeStatus.LOCKED
                        else -> NodeStatus.COMPLETED
                    },
                )
            )
            currentDate = currentDate.plusDays(1)
        }
        _uiState.value = _uiState.value.copy(nodeList = nodeList)
    }

    fun clearSelectedNode() {
        _uiState.value = _uiState.value.copy(selectedNode = null)
    }

    fun onNodeClicked(id: Int?) {
        val node = _uiState.value.nodeList.find { node -> node.id == id }
        if (node == null || node.status == NodeStatus.LOCKED) return

        _uiState.value = _uiState.value.copy(selectedNode = node)
    }
}