package com.snacks.nuvo.ui.login.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme
@Composable
internal fun RegisterForm(
    modifier: Modifier = Modifier,
    nicknameText: String,
    idText: String,
    passwordText: String,
    confirmPasswordText: String,
    onNicknameChange: (String) -> Unit,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    showPasswordMismatchError: Boolean,
) {
    Column(
        modifier = modifier.padding(horizontal = 38.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        RegisterInputField(
            label = "닉네임",
            placeholder = "닉네임 입력",
            value = nicknameText,
            onValueChange = onNicknameChange
        )

        Spacer(Modifier.height(36.dp))

        RegisterInputField(
            label = "아이디",
            placeholder = "아이디 입력",
            value = idText,
            onValueChange = onIdChange
        )

        Spacer(Modifier.height(36.dp))

        RegisterInputField(
            label = "비밀번호",
            placeholder = "비밀번호 입력",
            value = passwordText,
            onValueChange = onPasswordChange,
            isPassword = true
        )

        Spacer(Modifier.height(12.dp))

        RegisterInputField(
            label = "비밀번호 확인",
            placeholder = "비밀번호 확인",
            value = confirmPasswordText,
            onValueChange = onConfirmPasswordChange,
            isPassword = true,
            isError = showPasswordMismatchError,
        )
    }
}