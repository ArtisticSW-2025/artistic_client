package com.snacks.nuvo.ui.call.on_call

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.call.CallViewModel
import com.snacks.nuvo.ui.call.FakeUserRepository
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun OnCallScreen(
    viewModel: CallViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onCallEnded: () -> Unit
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    val minutes = uiState.elapsedTime / 60
    val seconds = uiState.elapsedTime % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 권한이 허용
        } else {
            // 권한이 거부
        }
    }

    val recordButtonFunction = {
        when (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)) {
            PackageManager.PERMISSION_GRANTED -> {
                if (uiState.isRecording) {
                    viewModel.stopListening()
                } else {
                    viewModel.startListening(context)
                }
            }
            else -> {
                permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }
    }

    val recordIconPainter = if (uiState.isRecording) {
        painterResource(R.drawable.ic_stop_filled)
    } else {
        painterResource(R.drawable.ic_microphone_filled)
    }

    val recordIconSize = if (uiState.isRecording) 40.dp else 59.dp

    LaunchedEffect(true) {
        viewModel.startTimer()
        delay(2000)
        viewModel.setIsEndPossible(true)
    }

    LaunchedEffect(key1 = uiState.callScripts.lastOrNull()) {
        val targetIndex = uiState.callScripts.size - 1

        if (targetIndex >= 0) {
            listState.animateScrollToItem(index = targetIndex)
        }
    }

    LaunchedEffect(uiState.score, uiState.feedbackContents) {
        if (uiState.score > 0 && uiState.feedbackContents.isNotEmpty()) {
            if (uiState.isTodayMission)
                viewModel.setIsTodayMissionFinish(true)
            else
                onCallEnded()
        }
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
            if (uiState.isTodayMission) {
                Image(
                    painter = painterResource(R.mipmap.call_character),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 322.dp)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_call_filled),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 322.dp)
                        .size(225.dp),
                )
            }
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
                                modifier = Modifier.padding(20.dp),
                                text = uiState.todayMission,
                                style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.mainGreen),
                            )
                        }

                        if (uiState.isTodayMissionFinish) {
                            TodayMissionFinishDialog(
                                modifier = Modifier,
                                date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) uiState.todayMissionDate!! else null,
                                onConfirm = {
                                    onNavigateBack()
                                    viewModel.setIsTodayMissionFinish(false)
                                },
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
                            viewModel.endCallAndGetFeedback()
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
                Button(
                    modifier = Modifier.size(80.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    onClick = recordButtonFunction
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
                            painter = recordIconPainter,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy),
                            modifier = Modifier.size(recordIconSize)
                        )
                    }
                }
            }
        }

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoadingIndicator()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "상태: 전화 중")
@Composable
fun CallScreenPreviewReceived() {
    val context = LocalContext.current
    val viewModel = remember { CallViewModel(
        savedStateHandle = SavedStateHandle(),
        userRepository = FakeUserRepository(),
        applicationContext = context
    ) }
    OnCallScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onCallEnded = { }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(name = "상태: 전화 중2")
@Composable
fun CallScreenPreviewPrevName() {
    val context = LocalContext.current
    val viewModel = remember { CallViewModel(
        savedStateHandle = SavedStateHandle(),
        userRepository = FakeUserRepository(),
        applicationContext = context
    ) }
    viewModel.startListening(context = LocalContext.current)
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
    val context = LocalContext.current
    val viewModel = remember { CallViewModel(
        savedStateHandle = SavedStateHandle(),
        userRepository = FakeUserRepository(),
        applicationContext = context
    ) }
    viewModel.setPrevName("오늘의 미션")
    viewModel.setIsTodayMission(true)
    viewModel.setTodayMission("오늘 하루를 요약해서 말해보자")
//    viewModel.setIsTodayMissionFinish(true)
    viewModel.setTodayMissionDateString(LocalDate.now().toString())
    OnCallScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onCallEnded = { }
    )
}