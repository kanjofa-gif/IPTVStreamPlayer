package com.iptvstream.ui.screens.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iptvstream.data.account.AccountProvider
import com.iptvstream.ui.theme.*

// =============================================================================
//  Reusable building blocks
// =============================================================================

@Composable
private fun ShoofLogo(size: Int = 80) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF1C2258), Color(0xFF080A26))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "S",
                color = Color.White,
                fontSize = (size * 0.30).sp,
                fontWeight = FontWeight.Black
            )
            Text(
                "H",
                color = Color.White,
                fontSize = (size * 0.30).sp,
                fontWeight = FontWeight.Black
            )
            Spacer(Modifier.width(2.dp))
            Box(
                modifier = Modifier
                    .size((size * 0.22).dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size((size * 0.16).dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1C58DC))
                )
            }
            Spacer(Modifier.width(1.dp))
            Box(
                modifier = Modifier
                    .size((size * 0.22).dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size((size * 0.16).dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1C58DC))
                )
            }
            Spacer(Modifier.width(2.dp))
            Text(
                "F",
                color = Color.White,
                fontSize = (size * 0.30).sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}

@Composable
private fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
    requestFocus: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(requestFocus) {
        if (requestFocus) runCatching { focusRequester.requestFocus() }
    }
    Row(
        modifier = modifier
            .focusRequester(focusRequester)
            .clip(RoundedCornerShape(28.dp))
            .background(if (isFocused) Color(0xFF22D3EE) else Primary)
            .scale(if (isFocused) 1.03f else 1f)
            .border(
                width = if (isFocused) 2.dp else 0.dp,
                color = Color.White,
                shape = RoundedCornerShape(28.dp)
            )
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            Icon(it, null, tint = Color.White, modifier = Modifier.size(20.dp))
            Spacer(Modifier.width(8.dp))
        }
        Text(text, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun OutlinedActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(28.dp))
            .background(if (isFocused) Primary else Color.Transparent)
            .border(
                width = 1.5.dp,
                color = if (isFocused) Color.White else TextSecondary,
                shape = RoundedCornerShape(28.dp)
            )
            .scale(if (isFocused) 1.03f else 1f)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = if (isFocused) Color.White else TextPrimary,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// =============================================================================
//  1. WELCOME — first sign-in screen
// =============================================================================

@Composable
fun AccountWelcomeScreen(
    onGoogleSignIn: () -> Unit,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null, tint = TextPrimary)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(horizontal = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShoofLogo(72)
            Spacer(Modifier.height(20.dp))
            Text(
                "مرحبًا بك في SHooF IPTV",
                color = Primary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "قم بتسجيل الدخول لمزامنة بياناتك عبر الأجهزة",
                color = TextSecondary,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(24.dp))
            PrimaryButton(
                text = "المتابعة باستخدام Google",
                onClick = onGoogleSignIn,
                leadingIcon = Icons.Default.AccountCircle,
                modifier = Modifier.fillMaxWidth(0.6f),
                requestFocus = true
            )

            Spacer(Modifier.height(32.dp))
            Text(
                "فوائد تسجيل الدخول:",
                color = TextPrimary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(Modifier.height(8.dp))
            BenefitItem("مزامنة قوائم التشغيل والمفضلات وتقدم المشاهدة على جميع أجهزتك")
            BenefitItem("تجربة سلسة عبر التلفزيون والجهاز اللوحي والهاتف")
            BenefitItem("اشتراكك متاح على جميع أجهزتك")

            Spacer(Modifier.height(16.dp))
            Text(
                "بتسجيل الدخول، فإنك توافق على سياسة الخصوصية",
                color = TextMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun BenefitItem(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, color = TextPrimary, fontSize = 13.sp)
        Spacer(Modifier.width(8.dp))
        Icon(
            Icons.Default.Check,
            null,
            tint = AccentGreen,
            modifier = Modifier.size(16.dp)
        )
    }
}

// =============================================================================
//  2. QR CODE — shows code for user to enter on phone
// =============================================================================

@Composable
fun AccountQRCodeScreen(
    userCode: String,
    verificationUrl: String,
    isPolling: Boolean,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                "تسجيل الدخول بحساب Google باستخدام هاتفك",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "انتقل إلى $verificationUrl أو امسح رمز QR وأدخل هذا الرمز:",
                color = TextSecondary,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(20.dp))

            // User code (big, prominent)
            Box(
                modifier = Modifier
                    .background(Surface, RoundedCornerShape(12.dp))
                    .padding(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(
                    userCode,
                    color = Color(0xFF22D3EE),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp
                )
            }

            Spacer(Modifier.height(20.dp))
            Text(verificationUrl, color = TextSecondary, fontSize = 12.sp)
            Spacer(Modifier.height(20.dp))

            // QR placeholder (a real QR generator can be added later)
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .background(Color.White, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.QrCode2,
                    null,
                    tint = Color.Black,
                    modifier = Modifier.size(150.dp)
                )
            }

            Spacer(Modifier.height(20.dp))
            if (isPolling) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        color = Primary,
                        strokeWidth = 2.dp
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("في انتظار التفويض...", color = TextSecondary, fontSize = 13.sp)
                }
            }

            Spacer(Modifier.height(20.dp))
            OutlinedActionButton(text = "إلغاء", onClick = onCancel)
        }
    }
}

// =============================================================================
//  3. CONFIRM — confirm continuing as signed-in Google user
// =============================================================================

@Composable
fun AccountConfirmScreen(
    displayName: String,
    email: String,
    onContinue: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().background(Background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(32.dp)
        ) {
            ShoofLogo(64)
            Spacer(Modifier.height(20.dp))
            Text(
                "تسجيل الدخول إلى SHooF IPTV",
                color = TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "باستخدام حساب Google",
                color = TextSecondary,
                fontSize = 13.sp
            )
            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .background(Surface, RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        displayName,
                        color = TextPrimary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(email, color = TextSecondary, fontSize = 13.sp)
                }
                Spacer(Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Primary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        displayName.firstOrNull()?.uppercase() ?: "U",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(Modifier.height(24.dp))
            PrimaryButton(
                text = "المتابعة باسم $displayName",
                onClick = onContinue,
                requestFocus = true
            )
            Spacer(Modifier.height(10.dp))
            OutlinedActionButton(text = "إلغاء", onClick = onCancel)
        }
    }
}

// =============================================================================
//  4. PROFILE — main account screen (after signed in)
// =============================================================================

@Composable
fun AccountProfileScreen(
    onManageLinkedAccounts: () -> Unit,
    onSignOut: () -> Unit,
    onBack: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val account = state.account

    Box(
        modifier = Modifier.fillMaxSize().background(Background)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null, tint = TextPrimary)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShoofLogo(96)
            Spacer(Modifier.height(16.dp))
            Text(
                account.displayName ?: "ضيف",
                color = Color(0xFF22D3EE),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                account.email ?: "غير مسجل دخول",
                color = TextPrimary,
                fontSize = 14.sp
            )
            Spacer(Modifier.height(8.dp))
            if (account.userId.isNotBlank()) {
                Text(
                    "ID: ${account.userId}",
                    color = TextMuted,
                    fontSize = 11.sp
                )
            }

            Spacer(Modifier.height(40.dp))

            PrimaryButton(
                text = "الحساب",
                onClick = onManageLinkedAccounts,
                modifier = Modifier.fillMaxWidth(0.5f),
                requestFocus = true
            )
            Spacer(Modifier.height(12.dp))
            if (account.isSignedIn) {
                OutlinedActionButton(
                    text = "تسجيل الخروج",
                    onClick = onSignOut,
                    modifier = Modifier.fillMaxWidth(0.5f)
                )
            }
        }
    }
}

// =============================================================================
//  5. LINKED ACCOUNTS — Google / Apple links
// =============================================================================

@Composable
fun AccountLinkedScreen(
    onBack: () -> Unit,
    onLinkGoogle: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val account = state.account

    Box(
        modifier = Modifier.fillMaxSize().background(Background)
    ) {
        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null, tint = TextPrimary)
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 64.dp, start = 48.dp, end = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("الحساب", color = TextPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(32.dp))
            Text(
                "الحسابات المرتبطة",
                color = TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(Modifier.height(20.dp))

            // Google row
            LinkedProviderRow(
                providerName = "Google",
                providerEmail = account.email ?: "—",
                icon = Icons.Default.AccountCircle,
                isLinked = account.googleId != null,
                onAction = {
                    if (account.googleId != null) {
                        viewModel.unlink(AccountProvider.GOOGLE)
                    } else {
                        onLinkGoogle()
                    }
                }
            )

            Spacer(Modifier.height(16.dp))

            // Apple row (placeholder — sign-in not implemented)
            LinkedProviderRow(
                providerName = "Apple",
                providerEmail = null,
                icon = Icons.Default.PhoneIphone,
                isLinked = account.appleId != null,
                onAction = { /* future implementation */ }
            )
        }
    }
}

@Composable
private fun LinkedProviderRow(
    providerName: String,
    providerEmail: String?,
    icon: ImageVector,
    isLinked: Boolean,
    onAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Surface, RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Action button (left side in RTL)
        var isFocused by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(
                    if (isLinked) Color.Transparent
                    else if (isFocused) Color(0xFF22D3EE)
                    else Color(0xFF22D3EE).copy(alpha = 0.85f)
                )
                .border(
                    width = 1.dp,
                    color = if (isLinked) TextSecondary else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .scale(if (isFocused) 1.05f else 1f)
                .onFocusChanged { isFocused = it.isFocused }
                .focusable()
                .clickable(onClick = onAction)
                .padding(horizontal = 20.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isLinked) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Check, null, tint = TextPrimary, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(4.dp))
                    Text("مرتبط", color = TextPrimary, fontSize = 13.sp)
                }
            } else {
                Text("ربط", color = Background, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Provider info (right side)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(horizontalAlignment = Alignment.End) {
                Text(providerName, color = TextPrimary, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                if (providerEmail != null && isLinked) {
                    Text(providerEmail, color = TextSecondary, fontSize = 12.sp)
                }
            }
            Spacer(Modifier.width(12.dp))
            Icon(icon, null, tint = TextPrimary, modifier = Modifier.size(28.dp))
        }
    }
}

// =============================================================================
//  6. TRIAL EXPIRED — lock screen after 7 days
// =============================================================================

@Composable
fun TrialExpiredScreen(
    onSignInAndSubscribe: () -> Unit,
    onSubscribeOnly: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Background, Color(0xFF120E2A))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(48.dp)
        ) {
            Icon(
                Icons.Default.Lock,
                null,
                tint = AccentOrange,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "انتهت تجربتك المجانية",
                color = TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "استمتعت بـ 7 أيام مجانية من SHooF IPTV.\nللمتابعة، اشترك بـ €4 لمرة واحدة فقط.",
                color = TextSecondary,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(40.dp))
            PrimaryButton(
                text = "اشترك الآن - €4",
                onClick = onSubscribeOnly,
                leadingIcon = Icons.Default.Star,
                modifier = Modifier.fillMaxWidth(0.6f),
                requestFocus = true
            )
            Spacer(Modifier.height(12.dp))
            OutlinedActionButton(
                text = "تسجيل الدخول بـ Google",
                onClick = onSignInAndSubscribe,
                modifier = Modifier.fillMaxWidth(0.6f)
            )

            Spacer(Modifier.height(32.dp))
            Text(
                "دفعة واحدة - وصول مدى الحياة",
                color = AccentGreen,
                fontSize = 12.sp
            )
        }
    }
}

// =============================================================================
//  7. TRIAL BANNER — small banner shown during 7-day trial
// =============================================================================

@Composable
fun TrialBanner(
    daysRemaining: Int,
    onSubscribe: () -> Unit
) {
    if (daysRemaining > 7 || daysRemaining < 0) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(AccentOrange.copy(alpha = 0.9f), Primary.copy(alpha = 0.9f))
                )
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var isFocused by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(if (isFocused) Color.White else Color.White.copy(alpha = 0.2f))
                .onFocusChanged { isFocused = it.isFocused }
                .focusable()
                .clickable(onClick = onSubscribe)
                .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
            Text(
                "اشترك الآن",
                color = if (isFocused) AccentOrange else Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.weight(1f))

        Text(
            "متبقي $daysRemaining ${if (daysRemaining == 1) "يوم" else "أيام"} من التجربة المجانية",
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.width(8.dp))
        Icon(
            Icons.Default.Schedule,
            null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
    }
}
