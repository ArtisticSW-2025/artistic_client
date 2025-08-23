package com.snacks.nuvo.ui.call.calling

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.call.CallStatus
import com.snacks.nuvo.ui.call.CallViewModel
import com.snacks.nuvo.ui.call.component.CallScreenLayout
import com.snacks.nuvo.ui.call.component.PulsingCircleAnimation
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme
import kotlinx.coroutines.delay

@Composable
internal fun CallingScreen(
    viewModel: CallViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onConnected: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        if (uiState.callStatus !== CallStatus.OUTGOING) return@LaunchedEffect

        delay(2000)
        onConnected()
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
                                onClick = onNavigateBack
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

        if (uiState.isLoading) {
            LoadingIndicator()
        }
    }
}

@Preview(name = "상태: 발신 중")
@Composable
fun CallScreenPreviewOutgoing() {
    val viewModel = remember { CallViewModel() }
    viewModel.setCallStatus(CallStatus.OUTGOING)
    CallingScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onConnected = { },
    )
}

@Preview(name = "상태: 수신 중")
@Composable
fun CallScreenPreviewIncoming() {
    val viewModel = remember { CallViewModel() }
    viewModel.setCallStatus(CallStatus.INCOMING)
    CallingScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onConnected = { },
    )
}