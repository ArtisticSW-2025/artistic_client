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
    emailText: String,
    passwordText: String,
    confirmPasswordText: String,
    onNicknameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    showPasswordMismatchError: Boolean,
    showPasswordLengthError: Boolean,
) {
    Column(
        modifier = modifier.padding(horizontal = 38.dp)
    ) {
        RegisterInputField(
            label = "닉네임",
            placeholder = "닉네임 입력",
            value = nicknameText,
            onValueChange = onNicknameChange
        )

        Spacer(Modifier.height(36.dp))

        RegisterInputField(
            label = "이메일",
            placeholder = "이메일 입력",
            value = emailText,
            onValueChange = onEmailChange ,
            errorMessage = if (emailText.isNotEmpty() && !emailText.contains("@")) {
                "올바른 이메일 주소를 입력해주세요."
            } else null
        )

        Spacer(Modifier.height(36.dp))

        RegisterInputField(
            label = "비밀번호",
            placeholder = "비밀번호 입력",
            value = passwordText,
            onValueChange = onPasswordChange,
            isPassword = true,
            errorMessage = if (showPasswordLengthError) "비밀번호는 8자 이상이어야 합니다." else null
        )

        Spacer(Modifier.height(12.dp))

        RegisterInputField(
            label = "비밀번호 확인",
            placeholder = "비밀번호 확인",
            value = confirmPasswordText,
            onValueChange = onConfirmPasswordChange,
            isPassword = true,
            errorMessage = if (showPasswordMismatchError) "비밀번호가 일치하지 않습니다." else null
        )
    }
}