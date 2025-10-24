package com.snacks.nuvo.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snacks.nuvo.data.preferences.UserPreferences
import com.snacks.nuvo.data.repository.FeedbackRepository
import com.snacks.nuvo.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val feedbackRepository: FeedbackRepository,
    private val userPreferences: UserPreferences,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _logoutEvent = MutableSharedFlow<Unit>()
    val logoutEvent: SharedFlow<Unit> = _logoutEvent.asSharedFlow()


    init {
        loadProfileData()
    }


    fun onEditProfileClick() {
        // 프로필 편집 로직
    }

    fun onLogoutClick() {
        viewModelScope.launch {
            // UserPreferences의 clearCredentials 함수를 직접 호출
            userPreferences.clearCredentials()
            // UI에 화면을 전환하라는 신호 보내기
            _logoutEvent.emit(Unit)
        }
    }

    private fun loadProfileData() {

            viewModelScope.launch {
                try {
                    _uiState.value = _uiState.value.copy(isLoading = true)

                    val userInfo = userRepository.getUserInfo()
                    val feedback = feedbackRepository.getFeedback()

                    val allFeedbacks = feedbackRepository.getFeedback()

                    // 2. ★★★ 데이터 필터링 ★★★
                    // totalFeedback에 'error' 필드가 없는, 즉 유효한 피드백만 필터링합니다.
                    val validFeedbacks = allFeedbacks.filter { feedbackData ->
                        feedbackData.totalFeedback.error == null
                    }

                    // Format missionCount to "00개" format
                    val formattedMissionCount = String.format("%02d", userInfo.missionCount)

                    // Format totalCallDuration (assuming seconds) to "0H 00M" format
                    val durationInSeconds = userInfo.totalCallDuration!!
                    val hours = durationInSeconds / 3600
                    val minutes = (durationInSeconds % 3600) / 60
                    val formattedDuration = "${hours}H ${String.format("%02d", minutes)}M"

                    _uiState.value = _uiState.value.copy(
                        userName = userInfo.username,
                        points = userInfo.points.toString(),
                        completedMissions = formattedMissionCount,
                        totalSpeakingTime = formattedDuration,
                        feedbackItems = validFeedbacks,
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