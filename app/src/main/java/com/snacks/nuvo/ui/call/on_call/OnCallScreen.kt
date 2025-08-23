package com.snacks.nuvo.ui.call.on_call

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.call.CallViewModel
import com.snacks.nuvo.ui.call.WaveformMode
import com.snacks.nuvo.ui.call.component.CallScreenLayout
import com.snacks.nuvo.ui.call.component.CallScriptCard
import com.snacks.nuvo.ui.call.component.TodayMissionFinishDialog
import com.snacks.nuvo.ui.call.component.WaveformComponent
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow
import kotlinx.coroutines.delay
import java.time.LocalDate

@Composable
internal fun OnCallScreen(
    viewModel: CallViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onCallEnded: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    val minutes = uiState.elapsedTime / 60
    val seconds = uiState.elapsedTime % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    LaunchedEffect(true) {
        viewModel.startTimer()
        delay(2000)
        viewModel.setIsEndPossible(true)
    }

    LaunchedEffect(key1 = uiState.callScripts.size) {
        listState.animateScrollToItem(index = uiState.callScripts.size - 1)
    }

    CallScreenLayout(
        modifier = Modifier.fillMaxSize(),
        onNavigateBack = onNavigateBack,
        prevName = uiState.prevName,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            NuvoTheme.colors.lightGradient1,
                            NuvoTheme.colors.lightGradient2
                        )
                    )
                ),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_call_filled),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 322.dp)
                    .size(225.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(65.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                        .height(266.dp),
                ) {
                    if (uiState.isTodayMission) {
                        Image(
                            modifier = Modifier
                                .padding(top = 42.dp, start = 26.dp, end = 26.dp)
                                .width(355.dp)
                                .height(184.dp),
                            painter = painterResource(R.drawable.ic_speech_balloon_filled),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = NuvoTheme.colors.white)
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 42.dp, start = 26.dp, end = 26.dp)
                                .width(355.dp)
                                .height(155.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = uiState.todayMission,
                                style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.mainGreen),
                            )
                        }

                        if (uiState.isTodayMissionFinish) {
                            TodayMissionFinishDialog(
                                modifier = Modifier,
                                date = uiState.todayMissionDate!!,
                                onConfirm = onNavigateBack,
                            )
                        }
                    } else {
                        LazyColumn (
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(210.dp),
                            state = listState,
                            userScrollEnabled = false,

                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                        ) {
                            items(uiState.callScripts) { script ->
                                CallScriptCard(
                                    modifier = Modifier,
                                    script = script.script,
                                    isAI = script.isAI,
                                    isLast = script.isLast
                                )
                            }
                        }
                    }
                }
                Spacer(Modifier.height(213.dp))
                if (uiState.isEndPossible) {
                    Button(
                        modifier = Modifier
                            .width(width = 166.dp)
                            .height(height = 40.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(100.dp),
                                offsetX = 0.dp,
                                offsetY = 4.dp,
                                blur = 4.dp,
                                spread = 0.dp,
                                color = NuvoTheme.colors.black.copy(alpha = 0.1f)
                            ),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = NuvoTheme.colors.white
                        ),
                        onClick = {
                            if (uiState.isTodayMission)
                                viewModel.setIsTodayMissionFinish()
                            else
                                onCallEnded()
                        }
                    ) {
                        Text(
                            text = "통화 종료하기",
                            style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.mainGreen),
                        )
                    }
                } else {
                    Spacer(Modifier.height(40.dp))
                }
                WaveformComponent(
                    modifier = Modifier,
                    mode = if (uiState.isRecording) WaveformMode.REALTIME else WaveformMode.MINIMAL,
                    realtimeLevels = uiState.waveformLevels,
                    maxLinesCount = uiState.waveformLineCount,
                )
                Box(
                    modifier = Modifier
                        .width(350.dp)
                        .height(1.dp)
                        .background(color = NuvoTheme.colors.white)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = timeFormatted,
                    style = NuvoTheme.typography.interRegular24.copy(color = NuvoTheme.colors.white),
                )
                Spacer(Modifier.height(25.dp))
                if (uiState.isRecording) {
                    Button(
                        modifier = Modifier
                            .size(80.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        onClick = { viewModel.stopRecording() }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(
                                    width = 1.dp,
                                    color = NuvoTheme.colors.subNavy,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_stop_filled),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy),
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .size(80.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                        ),
                        onClick = { viewModel.startRecording() }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(80.dp)
                                .border(
                                    width = 1.dp,
                                    color = NuvoTheme.colors.subNavy,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_microphone_filled),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy),
                                modifier = Modifier.size(59.dp)
                            )
                        }
                    }
                }
            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Preview(name = "상태: 전화 중")
@Composable
fun CallScreenPreviewReceived() {
    val viewModel = remember { CallViewModel() }
    OnCallScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onCallEnded = { }
    )
}

@Preview(name = "상태: 전화 중2")
@Composable
fun CallScreenPreviewPrevName() {
    val viewModel = remember { CallViewModel() }
    viewModel.startRecording()
    viewModel.setIsEndPossible(true)
    OnCallScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onCallEnded = { }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "상태: 오늘의 미션")
@Composable
fun CallScreenPreviewTodayMission() {
    val viewModel = remember { CallViewModel() }
    viewModel.setPrevName("오늘의 미션")
    viewModel.setIsTodayMission(true)
    viewModel.setTodayMission("오늘 하루를 요약해서 말해보자")
    viewModel.setIsTodayMissionFinish()
    viewModel.setTodayMissionDate(LocalDate.now())
    OnCallScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onCallEnded = { }
    )
}