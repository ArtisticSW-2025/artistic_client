package com.snacks.nuvo.ui.challenge.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.snacks.nuvo.R
import com.snacks.nuvo.ui.challenge.ChallengeNode
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodayMissionDialog(
    modifier: Modifier,
    node: ChallengeNode,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card (
            modifier = modifier
                .height(height = 381.dp)
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(
                        size = 15.dp,
                    ),
                    offsetX = 0.dp,
                    offsetY = 4.dp,
                    blur = 11.dp,
                    spread = 0.dp,
                    color = NuvoTheme.colors.black.copy(alpha = 0.2f),
                ),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            shape = RoundedCornerShape(size = 15.dp),
        ) {
            Surface (
                modifier = Modifier
                    .fillMaxSize(),
                color = Color.Transparent,
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = NuvoTheme.colors.mainGreen.copy(alpha = 0.8f))
                        .blur(radius = 5.dp),
                ) { }

                Box (
                    modifier = Modifier
                        .padding(all = 4.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(size = 30.dp),
                        onClick = onDismiss
                    ){
                        Icon (
                            painter = painterResource(id = R.drawable.ic_x_filled),
                            tint = NuvoTheme.colors.white,
                            contentDescription = null,
                        )
                    }
                }

                Column (
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(Modifier.height(height = 26.dp))
                    Text(
                        text = node.date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) ?: "",
                        style = NuvoTheme.typography.interMedium15.copy(color = NuvoTheme.colors.subLemon)
                    )
                    Spacer(Modifier.height(height = 11.dp))
                    Text(
                        text = "오늘의 미션",
                        style = NuvoTheme.typography.interBlack24.copy(color = NuvoTheme.colors.white)
                    )
                    Spacer(Modifier.height(height = 33.dp))

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .height(height = 236.dp)
                            .dropShadow(
                                shape = RoundedCornerShape(size = 15.dp),
                                offsetX = 0.dp,
                                offsetY = 4.dp,
                                blur = 11.dp,
                                spread = 0.dp,
                                color = NuvoTheme.colors.black.copy(alpha = 0.2f),
                            )
                            .background(color = Color.Transparent)
                    ){
                        Box(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(size = 15.dp))
                                .fillMaxSize()
                                .background(color = NuvoTheme.colors.white.copy(alpha = 0.8f))
                                .blur(radius = 5.dp)
                        ) { }

                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(Modifier.height(height = 53.dp))
                            Text(
                                text = node.description,
                                style = NuvoTheme.typography.interSemiBold15.copy(color = NuvoTheme.colors.gray6)
                            )
                            Spacer(Modifier.height(height = 102.dp))
                            Button (
                                modifier = Modifier
                                    .height(height = 45.dp)
                                    .width(width = 164.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = NuvoTheme.colors.mainGreen),
                                onClick = onConfirm,
                            ) {
                                Text (
                                    text = "START",
                                    style = NuvoTheme.typography.interSemiBold20.copy(color = NuvoTheme.colors.white)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewTodayMissionDialog() {
    TodayMissionDialog(
        modifier = Modifier,
        node = ChallengeNode(
            description = "오늘 하루를 요약해서 말해보자",
            date = LocalDate.now(),
        ),
        onConfirm = { } ,
        onDismiss = { }
    )
}