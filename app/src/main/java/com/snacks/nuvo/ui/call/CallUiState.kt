package com.snacks.nuvo.ui.call

import java.time.LocalDate

data class CallUiState(
    val isLoading: Boolean = false,
    val prevName: String = "",
    val callStatus: CallStatus? = null,
    val isReceived: Boolean = false,
    val contactName: String = "",

    val isRecording: Boolean = false,
    val waveformLineCount: Int = 14,
    val waveformLevels: List<Float> = List(waveformLineCount) { 0f },
    val isEndPossible: Boolean = false,
    val elapsedTime: Int = 0,

    val callScripts: List<CallScript> = emptyList<CallScript>(),

    val isTodayMission: Boolean = false,
    val todayMission: String = "",
    val todayMissionDate: LocalDate? = null,
    val isTodayMissionFinish: Boolean = false,
)

enum class CallStatus { OUTGOING, INCOMING }

enum class WaveformMode { MINIMAL, REALTIME }

data class CallScript(
    val script: String,
    val isAI: Boolean,
    val isLast: Boolean = false,
)