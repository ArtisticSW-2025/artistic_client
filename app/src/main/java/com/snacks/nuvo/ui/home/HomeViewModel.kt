package com.snacks.nuvo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.CallSessionRepository
import com.snacks.nuvo.data.repository.MissionRecordRepository
import com.snacks.nuvo.data.repository.UserRepository
import com.snacks.nuvo.network.model.response.CallSessionResponse
import com.snacks.nuvo.ui.script.ScriptItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val callSessionRepository: CallSessionRepository,
    private val missionRecordRepository: MissionRecordRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getUserInfo()
        getTodayMission()
        getRecommendScripts()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getUserInfo() {
        viewModelScope.launch {
            val userInfo = userRepository.getUserInfo()
            _uiState.value = _uiState.value.copy(
                userName = userInfo.username,
                rank = userInfo.rank ?: 0,
                score = userInfo.points
            )
        }
    }

    private fun getTodayMission() {
        viewModelScope.launch {
            val missionInfo = missionRecordRepository.getTodayMission()
            _uiState.value = _uiState.value.copy(
                todayMission = missionInfo.title,
                todayMissionId = missionInfo.id
            )
        }
    }

    private fun getRecommendScripts() {
        viewModelScope.launch {
            val scripts = callSessionRepository.getRandomScripts(count = 5)
            _uiState.value = _uiState.value.copy(
                recommendScripts = scripts
            )
        }
    }
}

class FakeCallSessionRepository : CallSessionRepository {
    override suspend fun getScripts(
        isCall: Boolean?,
        category: List<String>?,
        search: String?
    ): List<ScriptItem> {
        return listOf(
            ScriptItem(
                id = "1",
                title = "1",
                description = "1"
            ),
            ScriptItem(
                id = "2",
                title = "2",
                description = "2"
            ),
        )
    }

    override suspend fun getRandomScripts(
        count: Int,
        isBoolean: Boolean?,
        category: List<String>?
    ): List<RecommendScript> {
        return listOf(
            RecommendScript(
                id = "1",
                title = "1",
                description = "1"
            ),
            RecommendScript(
                id = "2",
                title = "2",
                description = "2"
            ),
            RecommendScript(
                id = "3",
                title = "3",
                description = "3"
            ),
            RecommendScript(
                id = "4",
                title = "4",
                description = "4"
            ),
            RecommendScript(
                id = "5",
                title = "5",
                description = "5"
            )
        )
    }

    override suspend fun getScriptDetail(id: String): CallSessionResponse {
        return CallSessionResponse(
            categories = emptyList(),
            isCall = true,
            mission = "",
            name = "",
            prompts = "",
            purpose = "",
            script = emptyList()
        )
    }

}