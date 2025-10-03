package com.snacks.nuvo.ui.call.result

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.call.CallViewModel
import com.snacks.nuvo.ui.call.FakeUserRepository
import com.snacks.nuvo.ui.call.component.CallScreenLayout
import com.snacks.nuvo.ui.component.ExpandableTextCard
import com.snacks.nuvo.ui.component.LoadingIndicator
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
internal fun CallResultScreen(
    viewModel: CallViewModel = viewModel(),
    onNavigateBack: () -> Unit,
    onRetry: () -> Unit,
    onNextScript: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        viewModel.startTimer()
        delay(2000)
        viewModel.setIsEndPossible(true)
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
            if (uiState.isDetailedResult) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 75.dp, bottom = 240.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "다음에는 이런 점을\n개선해보세요!",
                            style = NuvoTheme.typography.interSemiBold36.copy(color = NuvoTheme.colors.white),
                            textAlign = TextAlign.Center,
                            lineHeight = 50.sp
                        )
                        Spacer(Modifier.height(39.dp))
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 38.dp)
                                .fillMaxWidth()
                                .clip(shape = RoundedCornerShape(10.dp))
                                .background(color = NuvoTheme.colors.white.copy(alpha = 0.5f)),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(Modifier.height(9.dp))
                            Text(
                                text = "Feedback",
                                style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.mainGreen)
                            )
                            Spacer(Modifier.height(12.dp))
                            LazyColumn (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 18.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                            ) {
                                items(uiState.feedbackContents) { content ->
                                    ExpandableTextCard(
                                        modifier = Modifier,
                                        text = content,
                                        textStyle = NuvoTheme.typography.interBold14.copy(color = NuvoTheme.colors.mainGreen),
                                    )
                                }
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(bottom = 75.dp)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Column {
                        Button(
                            modifier = Modifier
                                .width(width = 320.dp)
                                .height(height = 60.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(100.dp),
                                    offsetX = 0.dp,
                                    offsetY = 4.dp,
                                    blur = 4.dp,
                                    spread = 0.dp,
                                ),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NuvoTheme.colors.white
                            ),
                            onClick = onRetry
                        ) {
                            Text(
                                text = "다시 연습하기",
                                style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.subNavy),
                            )
                        }
                        Spacer(Modifier.height(20.dp))
                        Button(
                            modifier = Modifier
                                .width(width = 320.dp)
                                .height(height = 60.dp)
                                .dropShadow(
                                    shape = RoundedCornerShape(100.dp),
                                    offsetX = 0.dp,
                                    offsetY = 4.dp,
                                    blur = 4.dp,
                                    spread = 0.dp,
                                ),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NuvoTheme.colors.white
                            ),
                            onClick = onNextScript
                        ) {
                            Text(
                                text = "다른 스크립트 도전하기",
                                style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.subNavy),
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(230.dp))
                    Text(
                        text = "성공적으로 \n통화 연습 완료!",
                        style = NuvoTheme.typography.interBlack36.copy(color = NuvoTheme.colors.white),
                        textAlign = TextAlign.Center,
                        lineHeight = 50.sp
                    )
                    Spacer(Modifier.height(22.dp))
                    Text(
                        text = uiState.result,
                        style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.subLemon)
                    )
                    Spacer(Modifier.height(39.dp))
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 38.dp)
                            .fillMaxWidth()
                            .height(100.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .background(color = NuvoTheme.colors.white.copy(alpha = 0.2f))
                            .clickable { viewModel.setIsDetailedResult(true) },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(Modifier.height(9.dp))
                        Text(
                            text = "score",
                            style = NuvoTheme.typography.interBlack16.copy(color = NuvoTheme.colors.darkGradient1)
                        )
                        Box(
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(color = NuvoTheme.colors.white)
                        )
                        Text(
                            text = uiState.score.toString(),
                            style = NuvoTheme.typography.interBlack36.copy(color = NuvoTheme.colors.white)
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .offset(y = 560.dp),
                    painter = painterResource(R.mipmap.call_character),
                    contentDescription = null
                )
            }
        }

        if (uiState.isLoading) {
            LoadingIndicator()
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
    CallResultScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onRetry = { },
        onNextScript = { }
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
    viewModel.setIsDetailedResult(true)
    CallResultScreen(
        viewModel = viewModel,
        onNavigateBack = { },
        onRetry = { },
        onNextScript = { }
    )
}