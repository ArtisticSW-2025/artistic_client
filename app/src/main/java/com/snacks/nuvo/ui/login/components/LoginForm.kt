package com.snacks.nuvo.ui.login.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoginForm(
    modifier: Modifier = Modifier,
    usernameText: String,
    passwordText: String,
    onUsernameTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = usernameText,
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onUsernameTextChange,
            placeholder = {
                Text(
                    text = "닉네임",
                    style = NuvoTheme.typography.pretendardRegular14,
                    color = NuvoTheme.colors.gray5
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(androidx.compose.ui.focus.FocusDirection.Down) }
            )
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp),
            value = passwordText,
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onPasswordTextChange,
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = "비밀번호",
                    style = NuvoTheme.typography.pretendardRegular14,
                    color = NuvoTheme.colors.gray5
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    onLoginClick()
                }
            )
        )
    }
}