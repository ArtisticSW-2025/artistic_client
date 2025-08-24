package com.snacks.nuvo.ui.login.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.snacks.nuvo.ui.theme.NuvoTheme

@Composable
internal fun LoginForm(
    modifier: Modifier = Modifier,
    idText: String,
    passwordText: String,
    onIdTextChange: (String) -> Unit,
    onPasswordTextChange: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .height(60.dp),
            value = idText,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onIdTextChange,
            label = { Text("아이디") }
        )

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 36.dp)
                .height(60.dp),
            value = passwordText,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = NuvoTheme.colors.mainGreen,
                unfocusedBorderColor = NuvoTheme.colors.gray3,
                cursorColor = NuvoTheme.colors.mainGreen,
                focusedLabelColor = NuvoTheme.colors.mainGreen,
                unfocusedLabelColor = NuvoTheme.colors.gray5
            ),
            onValueChange = onPasswordTextChange,
            label = { Text("비밀번호") }
        )
    }
}
