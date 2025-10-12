package com.snacks.nuvo.ui.call

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.snacks.nuvo.NuvoAppState
import com.snacks.nuvo.Routes
import com.snacks.nuvo.ui.call.calling.CallingScreen
import com.snacks.nuvo.ui.call.on_call.OnCallScreen
import com.snacks.nuvo.ui.call.result.CallResultScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CallNavHost(
    appState: NuvoAppState,
    viewModel: CallViewModel
) {
    val context = LocalContext.current

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.startAndBindService(context)
        } else {
            // TODO: 알림 권한 거부 시 처리
        }
    }

    val recordAudioPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                viewModel.startAndBindService(context)
            }
        } else {
            // TODO: 마이크 권한 거부 시 처리 (핵심 기능이므로 필수 안내 필요)
        }
    }

    DisposableEffect(Unit) {
        when (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)) {
            PackageManager.PERMISSION_GRANTED -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                        viewModel.startAndBindService(context)
                    } else {
                        notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                } else {
                    viewModel.startAndBindService(context)
                }
            }
            else -> {
                recordAudioPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }

        onDispose {
            viewModel.unbindService(context)
        }
    }

    val innerNavController = rememberNavController()

    NavHost(navController = innerNavController, startDestination = Routes.Call.CALL_ENTRY) {
        composable(Routes.Call.CALL_ENTRY) {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            if (!uiState.isLoading) {
                LaunchedEffect(uiState.isTodayMission) {
                    val destination = if (uiState.isTodayMission) {
                        Routes.Call.ON_CALL
                    } else {
                        Routes.Call.CALLING
                    }
                    innerNavController.navigate(destination) {
                        popUpTo(Routes.Call.CALL_ENTRY) { inclusive = true }
                    }
                }
            }
        }
        composable(Routes.Call.CALLING) {
            CallingScreen(
                onNavigateBack = { appState.navController.popBackStack() },
                viewModel = viewModel,
                onConnected = {
                    viewModel.setIsReceived(true)
                    innerNavController.navigate(Routes.Call.ON_CALL) {
                        popUpTo(Routes.Call.CALLING) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Call.ON_CALL) {
            OnCallScreen(
                viewModel = viewModel,
                onNavigateBack = { appState.navController.popBackStack() },
                onCallEnded = {
                    innerNavController.navigate(Routes.Call.RESULT) {
                        popUpTo(Routes.Call.ON_CALL) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.Call.RESULT) {
            CallResultScreen(
                viewModel = viewModel,
                onNavigateBack = { appState.navController.popBackStack() },
                onRetry = {
                    viewModel.resetCall()
                    innerNavController.navigate(Routes.Call.CALLING) {
                        popUpTo(Routes.Call.RESULT) { inclusive = true }
                    }
                },
                onNextScript = { /* 다른 스크립트 선택 화면으로 이동 */ }
            )
        }
    }
}