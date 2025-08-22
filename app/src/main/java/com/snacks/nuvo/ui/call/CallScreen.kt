package com.snacks.nuvo.ui.call

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.R
import com.snacks.nuvo.rememberNuvoAppState
import com.snacks.nuvo.ui.call.component.PulsingCircleAnimation
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun CallScreen(
    appState: NuvoAppState,
    viewModel: CallViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.receiveCallDelay()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Button(
            modifier = Modifier
                .zIndex(1f)
                .padding(24.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = NuvoTheme.colors.white,
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(0.dp),
            onClick = { appState.navController.popBackStack() }
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back_filled),
                contentDescription = null,
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = uiState.prevName,
                style = NuvoTheme.typography.interBlack15.copy(color = NuvoTheme.colors.white),
            )
            Spacer(Modifier.width(8.dp))
        }

        if (!uiState.isReceived && uiState.callStatus !== null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                NuvoTheme.colors.darkGradient1,
                                NuvoTheme.colors.darkGradient2
                            )
                        )
                    ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(188.dp))
                    Text(
                        modifier = Modifier
                            .height(23.dp),
                        text = if (uiState.callStatus == CallStatus.INCOMING) "mobile" else "",
                        style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.white),
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(
                        text = uiState.contactName,
                        style = NuvoTheme.typography.interBlack40.copy(color = NuvoTheme.colors.white),
                    )
                    Spacer(Modifier.height(18.dp))
                    Text(
                        modifier = Modifier
                            .height(23.dp),
                        text = if (uiState.callStatus == CallStatus.OUTGOING) "님께 전화 거는 중..." else "",
                        style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.white),
                    )
                    if (uiState.callStatus == CallStatus.OUTGOING) {
                        Spacer(Modifier.height(270.dp))
                        PulsingCircleAnimation(
                            modifier = Modifier.size(150.dp)
                        )
                    } else if (uiState.callStatus == CallStatus.INCOMING) {
                        Spacer(Modifier.height(342.dp))
                        Row {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    modifier = Modifier
                                        .size(84.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = NuvoTheme.colors.white
                                    ),
                                    onClick = { appState.navController.popBackStack() }
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_call_silent_filled),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = NuvoTheme.colors.gray4),
                                        modifier = Modifier.size(45.dp)
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "거절",
                                    style = NuvoTheme.typography.interBlack20.copy(color = NuvoTheme.colors.white),
                                )
                            }
                            Spacer(Modifier.width(107.dp))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Button(
                                    modifier = Modifier
                                        .size(84.dp),
                                    contentPadding = PaddingValues(0.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = NuvoTheme.colors.white
                                    ),
                                    onClick = { viewModel.setIsReceived(true) }
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.ic_calling_filled),
                                        contentDescription = null,
                                        colorFilter = ColorFilter.tint(color = NuvoTheme.colors.subNavy),
                                        modifier = Modifier.size(45.dp)
                                    )
                                }
                                Spacer(Modifier.height(16.dp))
                                Text(
                                    text = "수락",
                                    style = NuvoTheme.typography.interBlack20.copy(color = NuvoTheme.colors.white),
                                )
                            }
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

@SuppressLint("ViewModelConstructorInComposable")
@Preview
@Composable
fun CallScreenPreview(
    @PreviewParameter(CallScreenPreviewParameterProvider::class) num: Int
) {
    val viewModel = remember { CallViewModel() }

    LaunchedEffect(key1 = num) {
        if (num == 0) {
            viewModel.setCallStatus(CallStatus.OUTGOING)
        } else if (num == 1) {
            viewModel.setCallStatus(CallStatus.INCOMING)
        } else if (num == 2) {
            viewModel.setPrevName("오늘의 미션")
        }
    }

    CallScreen(
        appState = rememberNuvoAppState(),
        viewModel
    )
}

class CallScreenPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(
        0,
        1,
        2,
    )
}