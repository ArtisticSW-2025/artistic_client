package com.snacks.nuvo.ui.call

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(CallUiState())
    val uiState: StateFlow<CallUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = _uiState.value.copy(isLoading = true)
        getPrevName()
        getCallStatus()
        getContactName()
        _uiState.value = _uiState.value.copy(isLoading = false)
    }

    private fun getPrevName() {
        _uiState.value = _uiState.value.copy(prevName = "병원 초진 예약 전화")
    }

    fun setPrevName(prevName: String) {
        _uiState.value = _uiState.value.copy(prevName = prevName)
    }

    private fun getCallStatus() {
        _uiState.value = _uiState.value.copy(callStatus = CallStatus.OUTGOING)
    }

    suspend fun receiveCallDelay() {
        if (_uiState.value.callStatus !== CallStatus.OUTGOING) return

        delay(2000)
        setIsReceived(true)
    }

    fun setCallStatus(callStatus: CallStatus?) {
        _uiState.value = _uiState.value.copy(callStatus = callStatus)
    }

    fun setIsReceived(isReceived: Boolean) {
        _uiState.value = _uiState.value.copy(isReceived = isReceived)
    }

    fun getContactName() {
        _uiState.value = _uiState.value.copy(contactName = "힐링 병원")
    }
}