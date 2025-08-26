package com.snacks.nuvo.ui.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme
@Composable
internal fun RegisterInputField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    isError: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = NuvoTheme.typography.interSemiBold15,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            value = value,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (isError) Color(0xFFFF0E0E) else NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = if (isError) Color(0xFFFF0E0E) else NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onValueChange,
            label = { Text(placeholder) },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            isError = isError
        )
        if(isError){
            Text(
                text = "비밀번호가 일치하지 않습니다",
                color = Color(0xFFFF0E0E),
                style = NuvoTheme.typography.interRegular15,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
            )
        }
    }
}