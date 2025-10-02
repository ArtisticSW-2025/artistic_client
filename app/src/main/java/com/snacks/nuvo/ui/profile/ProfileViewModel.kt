package com.snacks.nuvo.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.repository.FeedbackRepository
import com.snacks.nuvo.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val feedbackRepository: FeedbackRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _userId = MutableStateFlow<String?>(null)

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        loadProfileData()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }


    fun onEditProfileClick() {
        // 프로필 편집 로직
    }

    private fun loadProfileData() {

            viewModelScope.launch {
                try {
                    _uiState.value = _uiState.value.copy(isLoading = true)

                    val userInfo = userRepository.getUserInfo()
                    val feedback = feedbackRepository.getFeedback()

                    _uiState.value = _uiState.value.copy(
                        userName = userInfo.username,
                        points = userInfo.points.toString(),
                        completedMissions = if (userInfo.missionCount != 0) userInfo.missionCount.toString() else _uiState.value.completedMissions,
                        totalSpeakingTime = if (userInfo.totalCallDuration != 0) userInfo.totalCallDuration.toString() else _uiState.value.totalSpeakingTime,
                        feedbackItems = feedback,
                        isLoading = false
                    )

                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false
                    )
                    // TODO: 에러 처리
                }
        }
    }

    fun refreshData() {
        loadProfileData()
    }
}