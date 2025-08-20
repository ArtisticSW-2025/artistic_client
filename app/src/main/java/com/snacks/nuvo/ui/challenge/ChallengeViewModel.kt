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
        getToday()
        getPhrase()
        getWeeklyMission()
        getDailyNodeList()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getToday() {
        _uiState.value = _uiState.value.copy(today = LocalDate.now())
    }

    private fun getPhrase() {
        _uiState.value = _uiState.value.copy(phrase = "오늘의 한 마디, 내일의 자신감!")
    }

    private fun getWeeklyMission() {
        _uiState.value = _uiState.value.copy(weeklyMission = "30분 이상 통화하기")
    }

    private fun getDailyNodeList() {
        val today = LocalDate.now()
        val firstDayOfMonth = today.withDayOfMonth(1)
        val lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth())

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
                        currentDate.isAfter(today) -> NodeStatus.LOCKED
                        currentDate.isEqual(today) -> NodeStatus.UNLOCKED
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
        _uiState.value = _uiState.value.copy(
            selectedNode = _uiState.value.nodeList.find { node -> node.id == id }
        )
    }
}