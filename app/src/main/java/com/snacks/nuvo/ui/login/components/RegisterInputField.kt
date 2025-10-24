package com.snacks.nuvo.ui.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
    errorMessage: String? = null
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
        Text(
            text = label,
            style = NuvoTheme.typography.pretendardSemiBold16,
            color = NuvoTheme.colors.gray6,
            modifier = Modifier.padding(horizontal = 4.dp)
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = value,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = if (errorMessage != null) Color(0xFFFF0E0E) else NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = if (errorMessage != null) Color(0xFFFF0E0E) else NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    style = NuvoTheme.typography.pretendardRegular14,
                    color = NuvoTheme.colors.gray5
                )
            },
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            isError = errorMessage != null,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
            )
        )
        if(errorMessage != null){
            Text(
                text = errorMessage,
                color = Color(0xFFFF0E0E),
                style = NuvoTheme.typography.pretendardRegular12,
            )
        }
    }
}