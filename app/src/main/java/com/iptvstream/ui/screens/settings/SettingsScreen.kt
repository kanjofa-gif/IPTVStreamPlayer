package com.iptvstream.ui.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.ui.theme.*

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize().background(Background)) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Back button sidebar
            Column(
                modifier = Modifier.width(60.dp).fillMaxHeight().background(SurfaceVariant),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(16.dp))
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "رجوع", tint = TextPrimary)
                }
            }

            // Settings content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text("الإعدادات", color = TextPrimary, fontSize = 26.sp, fontWeight = FontWeight.Bold)

                Divider(color = Divider)

                // Home section
                SettingsSection(title = "الرئيسية") {
                    SettingSubtitle("تكوين صف القناة الرئيسية")
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(Surface)
                            .border(1.dp, Primary, RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text("تمت مشاهدته مؤخراً", color = TextPrimary, fontSize = 15.sp)
                    }
                }

                Divider(color = Divider)

                // Series section
                SettingsSection(title = "المسلسلات") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("تشغيل الحلقة التالية عند الانتهاء", color = TextPrimary, fontSize = 15.sp)
                        Spacer(Modifier.width(12.dp))
                        Checkbox(
                            checked = state.autoNextEpisode,
                            onCheckedChange = viewModel::setAutoNextEpisode,
                            colors = CheckboxDefaults.colors(checkedColor = Primary)
                        )
                    }
                }

                Divider(color = Divider)

                // Watch tracking section
                SettingsSection(
                    title = "متابعة المشاهدة",
                    subtitle = "سيتم حفظ الأفلام والحلقات بين وقت البدء والنهاية لمتابعة المشاهدة"
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Surface)
                                    .border(1.dp, Color(0xFF444444), RoundedCornerShape(16.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${state.watchFromMin}", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("دقائق من البداية", color = TextSecondary, fontSize = 13.sp)
                            Spacer(Modifier.height(4.dp))
                            Row {
                                IconButton(onClick = { viewModel.setWatchFromMin(state.watchFromMin - 1) }) {
                                    Text("-", color = TextPrimary, fontSize = 20.sp)
                                }
                                IconButton(onClick = { viewModel.setWatchFromMin(state.watchFromMin + 1) }) {
                                    Text("+", color = TextPrimary, fontSize = 20.sp)
                                }
                            }
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(Surface)
                                    .border(1.dp, Color(0xFF444444), RoundedCornerShape(16.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${state.watchToMin}", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.height(8.dp))
                            Text("دقائق حتى النهاية", color = TextSecondary, fontSize = 13.sp)
                            Spacer(Modifier.height(4.dp))
                            Row {
                                IconButton(onClick = { viewModel.setWatchToMin(state.watchToMin - 1) }) {
                                    Text("-", color = TextPrimary, fontSize = 20.sp)
                                }
                                IconButton(onClick = { viewModel.setWatchToMin(state.watchToMin + 1) }) {
                                    Text("+", color = TextPrimary, fontSize = 20.sp)
                                }
                            }
                        }
                    }
                }

                Divider(color = Divider)

                // Parental Control
                SettingsSection(
                    title = "الرقابة الأبوية",
                    subtitle = "احم المحتوى برمز PIN"
                ) {
                    Button(
                        onClick = viewModel::showPinDialog,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = TextPrimary
                        ),
                        border = BorderStroke(1.dp, Color(0xFF444444)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("تعيين رمز PIN", fontSize = 15.sp)
                    }
                }

                Divider(color = Divider)

                // Privacy & Terms
                SettingsSection(title = "سياسة الخصوصية") {
                    OutlinedButton(
                        onClick = {},
                        border = BorderStroke(1.dp, Color(0xFF444444)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("سياسة الخصوصية", color = TextPrimary)
                    }
                }

                SettingsSection(title = "شروط الاستخدام") {
                    OutlinedButton(
                        onClick = {},
                        border = BorderStroke(1.dp, Color(0xFF444444)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("شروط الاستخدام", color = TextPrimary)
                    }
                }

                OutlinedButton(
                    onClick = {},
                    border = BorderStroke(1.dp, Color(0xFF444444)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("الإعدادات المتقدمة", color = TextPrimary)
                }

                Spacer(Modifier.height(24.dp))

                // Version info
                Text("الإصدار 187-1.0.0", color = TextMuted, fontSize = 13.sp)
                Text("App id: ${state.appId}", color = TextMuted, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    subtitle: String? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(title, color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        if (subtitle != null) {
            Text(subtitle, color = TextSecondary, fontSize = 13.sp, textAlign = TextAlign.End)
        }
        content()
    }
}

@Composable
fun SettingSubtitle(text: String) {
    Text(text, color = TextSecondary, fontSize = 13.sp, textAlign = TextAlign.End)
}
