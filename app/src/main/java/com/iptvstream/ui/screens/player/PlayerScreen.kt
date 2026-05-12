package com.iptvstream.ui.screens.player

import android.util.Log
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.iptvstream.ui.PlayerHolder
import kotlinx.coroutines.delay

@Composable
fun PlayerScreen(
    type: String,
    id: String,
    url: String,
    title: String,
    icon: String,
    viewModel: PlayerViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var isControlsVisible by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isPlaying by remember { mutableStateOf(true) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }
    var isBuffering by remember { mutableStateOf(true) }

    var currentItem by remember {
        mutableStateOf(PlayerHolder.current ?: com.iptvstream.ui.PlayItem(type, id, url, title, icon))
    }

    Log.d("SHooF", "PlayerScreen url=${currentItem.url}")

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    errorMessage = "خطأ ${error.errorCode}: ${error.message}"
                    Log.e("SHooF", "Player error", error)
                }
                override fun onIsPlayingChanged(playing: Boolean) { isPlaying = playing }
                override fun onPlaybackStateChanged(state: Int) {
                    isBuffering = state == Player.STATE_BUFFERING
                }
            })
        }
    }

    LaunchedEffect(currentItem.url) {
        try {
            errorMessage = null
            if (currentItem.url.isNotBlank()) {
                exoPlayer.setMediaItem(MediaItem.fromUri(currentItem.url))
                exoPlayer.prepare()
            }
        } catch (e: Exception) {
            errorMessage = "فشل التحميل: ${e.message}"
        }
    }

    LaunchedEffect(exoPlayer) {
        while (true) {
            currentPosition = exoPlayer.currentPosition
            duration = exoPlayer.duration.takeIf { it > 0 } ?: 0
            delay(500)
        }
    }

    LaunchedEffect(isControlsVisible, isPlaying) {
        if (isControlsVisible && isPlaying && !isBuffering) {
            delay(5000)
            isControlsVisible = false
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                if (exoPlayer.currentPosition > 0) {
                    viewModel.saveProgress(
                        id = currentItem.id, name = currentItem.title, icon = currentItem.icon,
                        url = currentItem.url, type = currentItem.type,
                        position = exoPlayer.currentPosition,
                        duration = exoPlayer.duration.takeIf { it > 0 } ?: 0
                    )
                }
                exoPlayer.release()
            } catch (e: Exception) {}
        }
    }

    fun togglePlayPause() {
        if (exoPlayer.isPlaying) exoPlayer.pause() else exoPlayer.play()
        isControlsVisible = true
    }

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        try { focusRequester.requestFocus() } catch (e: Exception) {}
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .focusRequester(focusRequester)
            .focusable()
            .onKeyEvent { event ->
                if (event.type != KeyEventType.KeyDown) return@onKeyEvent false
                when (event.nativeKeyEvent.keyCode) {
                    KeyEvent.KEYCODE_DPAD_UP -> {
                        if (currentItem.type == "live") {
                            PlayerHolder.previous()?.let { currentItem = it }
                            true
                        } else false
                    }
                    KeyEvent.KEYCODE_DPAD_DOWN -> {
                        if (currentItem.type == "live") {
                            PlayerHolder.next()?.let { currentItem = it }
                            true
                        } else false
                    }
                    KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                        togglePlayPause()
                        true
                    }
                    KeyEvent.KEYCODE_DPAD_LEFT -> {
                        if (currentItem.type != "live") {
                            exoPlayer.seekTo((exoPlayer.currentPosition - 10_000).coerceAtLeast(0))
                            isControlsVisible = true
                            true
                        } else false
                    }
                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
                        if (currentItem.type != "live") {
                            exoPlayer.seekTo(exoPlayer.currentPosition + 10_000)
                            isControlsVisible = true
                            true
                        } else false
                    }
                    KeyEvent.KEYCODE_MEDIA_PLAY,
                    KeyEvent.KEYCODE_MEDIA_PAUSE,
                    KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,
                    KeyEvent.KEYCODE_SPACE -> {
                        togglePlayPause()
                        true
                    }
                    KeyEvent.KEYCODE_BACK -> {
                        onBack()
                        true
                    }
                    else -> false
                }
            }
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                    keepScreenOn = true
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (isBuffering && errorMessage == null) {
            CircularProgressIndicator(
                color = Color.White,
                modifier = Modifier.align(Alignment.Center).size(64.dp),
                strokeWidth = 4.dp
            )
        }

        if (errorMessage != null) {
            Column(
                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("خطأ في التشغيل", color = Color.White, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))
                Text(errorMessage!!, color = Color.Gray, fontSize = 13.sp)
                Spacer(Modifier.height(16.dp))
                Button(onClick = onBack) { Text("رجوع") }
            }
        }

        AnimatedVisibility(
            visible = isControlsVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            PlayerControls(
                title = currentItem.title,
                icon = currentItem.icon,
                type = currentItem.type,
                position = currentPosition,
                duration = duration,
                isPlaying = isPlaying,
                onPlayPause = ::togglePlayPause,
                onSeek = { exoPlayer.seekTo(it) },
                onSeekForward = { exoPlayer.seekTo(exoPlayer.currentPosition + 10_000) },
                onSeekBackward = { exoPlayer.seekTo((exoPlayer.currentPosition - 10_000).coerceAtLeast(0)) },
                onRestart = { exoPlayer.seekTo(0) },
                onNext = { PlayerHolder.next()?.let { currentItem = it } },
                onPrevious = { PlayerHolder.previous()?.let { currentItem = it } },
                onBack = onBack
            )
        }
    }
}

@Composable
fun PlayerControls(
    title: String,
    icon: String,
    type: String,
    position: Long,
    duration: Long,
    isPlaying: Boolean,
    onPlayPause: () -> Unit,
    onSeek: (Long) -> Unit,
    onSeekForward: () -> Unit,
    onSeekBackward: () -> Unit,
    onRestart: () -> Unit,
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    onBack: () -> Unit
) {
    val playFocus = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.7f),
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.85f)
                    )
                )
            )
    ) {
        if (title.isNotBlank()) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopEnd).padding(end = 24.dp, top = 24.dp)
            )
        }

        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Icon(Icons.Default.ArrowBack, null, tint = Color.White, modifier = Modifier.size(32.dp))
        }

        if (icon.isNotBlank() && title.isNotBlank() && type != "live") {
            AsyncImage(
                model = icon,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 32.dp)
                    .width(120.dp)
                    .aspectRatio(2f / 3f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.DarkGray)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (type != "live") {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(formatDuration(position), color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                        val progress = if (duration > 0) position.toFloat() / duration.toFloat() else 0f
                        Slider(
                            value = progress.coerceIn(0f, 1f),
                            onValueChange = { onSeek((it * duration).toLong()) },
                            modifier = Modifier.weight(1f),
                            colors = SliderDefaults.colors(
                                thumbColor = Color.White,
                                activeTrackColor = Color(0xFF2196F3),
                                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                            )
                        )
                        Text(formatDuration(duration), color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Medium)
                    }
                }
            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(Color.Red.copy(alpha = 0.8f), RoundedCornerShape(6.dp))
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text("● مباشر", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ControlButton(
                    icon = Icons.Default.SkipPrevious,
                    label = if (type == "live") "السابقة" else "السابق",
                    onClick = onPrevious
                )
                Spacer(Modifier.width(20.dp))

                if (type != "live") {
                    ControlButton(icon = Icons.Default.Replay10, label = "10 ث", onClick = onSeekBackward)
                    Spacer(Modifier.width(20.dp))
                }

                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .focusRequester(playFocus)
                        .focusable()
                        .clickable(onClick = onPlayPause),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        null,
                        tint = Color.Black,
                        modifier = Modifier.size(44.dp)
                    )
                }

                if (type != "live") {
                    Spacer(Modifier.width(20.dp))
                    ControlButton(icon = Icons.Default.Forward10, label = "10 ث", onClick = onSeekForward)
                    Spacer(Modifier.width(20.dp))
                    ControlButton(icon = Icons.Default.Replay, label = "إعادة", onClick = onRestart)
                }

                Spacer(Modifier.width(20.dp))
                ControlButton(
                    icon = Icons.Default.SkipNext,
                    label = if (type == "live") "التالية" else "التالي",
                    onClick = onNext
                )
            }
        }
    }
}

@Composable
fun ControlButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isFocused) Color.White.copy(alpha = 0.25f) else Color.Transparent)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(icon, null, tint = Color.White, modifier = Modifier.size(if (isFocused) 32.dp else 28.dp))
        Spacer(Modifier.height(4.dp))
        Text(label, color = Color.White, fontSize = 11.sp, fontWeight = if (isFocused) FontWeight.Bold else FontWeight.Normal)
    }
}

private fun formatDuration(ms: Long): String {
    if (ms <= 0) return "00:00"
    val totalSec = ms / 1000
    val h = totalSec / 3600
    val m = (totalSec % 3600) / 60
    val s = totalSec % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, s) else "%02d:%02d".format(m, s)
}
