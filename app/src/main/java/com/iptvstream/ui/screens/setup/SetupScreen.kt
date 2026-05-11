package com.iptvstream.ui.screens.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.theme.*

@Composable
fun SetupScreen(
    viewModel: SetupViewModel = hiltViewModel(),
    onSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(state.navigateToHome) {
        if (state.navigateToHome) onSuccess()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.width(900.dp).padding(32.dp)
        ) {
            // Illustration
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF1E3A5F), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(60.dp, 44.dp)
                            .background(Color(0xFF2E5A8F), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(Color(0xFFE53935), shape = androidx.compose.foundation.shape.CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("▶", color = Color.White, fontSize = 8.sp)
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        repeat(4) {
                            Box(modifier = Modifier.size(12.dp, 18.dp).background(Color(0xFFE53935), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)))
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "إضافة قائمة تشغيل",
                color = TextPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )

            // Form grid
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                IPTVTextField(
                    value = state.url,
                    onValueChange = viewModel::onUrlChanged,
                    placeholder = "الرابط",
                    modifier = Modifier.weight(1f).focusRequester(focusRequester)
                )
                IPTVTextField(
                    value = state.name,
                    onValueChange = viewModel::onNameChanged,
                    placeholder = "اسم قائمة التشغيل",
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                IPTVTextField(
                    value = state.password,
                    onValueChange = viewModel::onPasswordChanged,
                    placeholder = "كلمة المرور",
                    isPassword = true,
                    modifier = Modifier.weight(1f)
                )
                IPTVTextField(
                    value = state.username,
                    onValueChange = viewModel::onUsernameChanged,
                    placeholder = "اسم المستخدم",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (state.error != null) {
                    Text(
                        text = state.error!!,
                        color = AccentRed,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                }

                Button(
                    onClick = viewModel::addPlaylist,
                    enabled = !state.isLoading && state.url.isNotBlank(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text("تسجيل دخول", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }

            Text(
                text = "تسجيل الدخول باستخدام الرابط (غير موصى به)",
                color = TextMuted,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
fun IPTVTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = TextMuted,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Primary,
            unfocusedBorderColor = Color(0xFF333333),
            focusedContainerColor = Surface,
            unfocusedContainerColor = Surface,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = Primary
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End),
        modifier = modifier.height(56.dp)
    )
}
