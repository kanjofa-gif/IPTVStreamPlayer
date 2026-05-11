package com.iptvstream.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// ─── Colors ───────────────────────────────────────────────────────────────────

val Background = Color(0xFF0A0A0A)
val Surface = Color(0xFF1A1A1A)
val SurfaceVariant = Color(0xFF242424)
val Primary = Color(0xFF4A90D9)
val PrimaryVariant = Color(0xFF2A6FAF)
val Secondary = Color(0xFF6B6B6B)
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFFAAAAAA)
val TextMuted = Color(0xFF666666)
val Accent = Color(0xFF4A90D9)
val AccentGreen = Color(0xFF4CAF50)
val AccentRed = Color(0xFFE53935)
val AccentOrange = Color(0xFFFF9800)
val FocusBorder = Color(0xFF4A90D9)
val CardBackground = Color(0xFF1E1E1E)
val Divider = Color(0xFF2A2A2A)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = Color.White,
    primaryContainer = PrimaryVariant,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    surfaceVariant = SurfaceVariant,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary
)

@Composable
fun IPTVTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
