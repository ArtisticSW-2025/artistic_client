package com.snacks.nuvo.ui.call.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.snacks.nuvo.ui.theme.NuvoTheme
import com.snacks.nuvo.util.dropShadow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TodayMissionFinishDialog(
    modifier: Modifier = Modifier,
    date: LocalDate?,
    onConfirm: () -> Unit
) {
    Dialog(onDismissRequest = onConfirm) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.5f)
        Card (
            modifier = modifier
                .width(width = 262.dp)
                .height(height = 191.dp)
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
                        .background(color = NuvoTheme.colors.white),
                ) { }

                Column (
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(Modifier.height(height = 26.dp))
                    if (date != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(
                            text = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) ?: "",
                            style = NuvoTheme.typography.interMedium15.copy(color = NuvoTheme.colors.mainGreen)
                        )
                    }
                    Spacer(Modifier.height(height = 29.dp))
                    Text(
                        text = "CLEAR",
                        style = NuvoTheme.typography.interSemiBold24.copy(color = NuvoTheme.colors.subNavy)
                    )
                    Spacer(Modifier.height(height = 29.dp))
                    Button (
                        modifier = Modifier
                            .height(height = 43.dp)
                            .width(width = 218.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = NuvoTheme.colors.mainGreen),
                        onClick = onConfirm,
                    ) {
                        Text (
                            text = "확인",
                            style = NuvoTheme.typography.pretendardSemiBold15.copy(color = NuvoTheme.colors.white)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun TodayMissionFinishDialogPreview() {
    TodayMissionFinishDialog(
        date = LocalDate.now(),
        onConfirm = { } ,
    )
}