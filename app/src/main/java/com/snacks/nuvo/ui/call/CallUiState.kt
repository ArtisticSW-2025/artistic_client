package com.snacks.nuvo.ui.call

data class CallUiState(
    val isLoading: Boolean = false,
    val prevName: String = "",
    val callStatus: CallStatus? = null,
    val isReceived: Boolean = false,
    val contactName: String = "",
)

enum class CallStatus { OUTGOING, INCOMING }