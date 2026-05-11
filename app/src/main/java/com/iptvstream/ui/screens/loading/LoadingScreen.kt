package com.iptvstream.ui.screens.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.theme.*

@Composable
fun LoadingScreen(
    viewModel: LoadingViewModel = hiltViewModel(),
    onComplete: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isComplete) {
        if (state.isComplete) onComplete()
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.padding(48.dp)
        ) {
            // Cinema illustration
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(Color(0xFF1E3A5F), RoundedCornerShape(20.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(90.dp, 65.dp)
                            .background(Color(0xFF2E5A8F), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color(0xFFE53935), shape = androidx.compose.foundation.shape.CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("▶", color = Color.White, fontSize = 14.sp)
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Box(
                            modifier = Modifier
                                .size(28.dp, 10.dp)
                                .background(Color(0xFF4A90D9), RoundedCornerShape(4.dp))
                                .padding(bottom = 4.dp)
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(4) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp, 24.dp)
                                        .background(Color(0xFFE53935), RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                                )
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = "يرجى الانتظار أثناء تحديث البيانات...",
                color = TextPrimary,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            LinearProgressIndicator(
                progress = { state.progress },
                modifier = Modifier
                    .width(400.dp)
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = Primary,
                trackColor = Surface
            )

            Text(
                text = state.currentStep,
                color = TextSecondary,
                fontSize = 13.sp
            )
        }
    }
}
