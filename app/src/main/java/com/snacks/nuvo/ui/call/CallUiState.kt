package com.snacks.nuvo.ui.call

import android.os.Build
import androidx.annotation.RequiresApi
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
    val todayMissionDateString: String = "",
    val isTodayMissionFinish: Boolean = false,

    val isDetailedResult: Boolean = false,
    val result: String = "",
    val feedbackContents: List<String> = emptyList<String>(),
    val score: Int = 0,
) {
    val todayMissionDate: LocalDate?
        @RequiresApi(Build.VERSION_CODES.O)
        get() {
            return if (todayMissionDateString.isNotEmpty()) {
                runCatching { LocalDate.parse(todayMissionDateString) }.getOrNull()
            } else {
                null
            }
        }
}

enum class CallStatus { OUTGOING, INCOMING }

enum class WaveformMode { MINIMAL, REALTIME }

data class CallScript(
    val script: String,
    val isAI: Boolean,
    val isLast: Boolean = false,
)