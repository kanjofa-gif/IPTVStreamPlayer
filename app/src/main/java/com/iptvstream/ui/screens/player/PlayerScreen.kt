package com.iptvstream.ui.screens.player

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
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
    val state by viewModel.state.collectAsState()
    var isControlsVisible by remember { mutableStateOf(true) }

    LaunchedEffect(url) {
        viewModel.setUrl(url, title, icon)
    }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().also { player ->
            player.playWhenReady = true
        }
    }

    LaunchedEffect(url) {
        if (url.isNotBlank()) {
            exoPlayer.setMediaItem(MediaItem.fromUri(url))
            exoPlayer.prepare()
        }
    }

    LaunchedEffect(isControlsVisible) {
        if (isControlsVisible) {
            delay(5000)
            isControlsVisible = false
        }
    }

    LaunchedEffect(exoPlayer) {
        while (true) {
            delay(10000)
            if (exoPlayer.isPlaying) {
                viewModel.saveProgress(
                    id = id,
                    name = title,
                    icon = icon,
                    url = url,
                    type = type,
                    position = exoPlayer.currentPosition,
                    duration = exoPlayer.duration.takeIf { it > 0 } ?: 0
                )
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.saveProgress(
                id = id,
                name = title,
                icon = icon,
                url = url,
                type = type,
                position = exoPlayer.currentPosition,
                duration = exoPlayer.duration.takeIf { it > 0 } ?: 0
            )
            exoPlayer.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { isControlsVisible = !isControlsVisible }
    ) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        AnimatedVisibility(
            visible = isControlsVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
                ) {
                    Icon(Icons.Default.ArrowBack, null, tint = Color.White, modifier = Modifier.size(28.dp))
                }

                if (title.isNotBlank()) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.TopCenter).padding(top = 20.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val position = exoPlayer.currentPosition
                    val duration = exoPlayer.duration.takeIf { it > 0 } ?: 1L
                    val progress = position.toFloat() / duration.toFloat()

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(formatDuration(position), color = Color.White, fontSize = 14.sp)
                        Slider(
                            value = progress.coerceIn(0f, 1f),
                            onValueChange = { exoPlayer.seekTo((it * duration).toLong()) },
                            modifier = Modifier.weight(1f),
                            colors = SliderDefaults.colors(
                                thumbColor = Color.White,
                                activeTrackColor = Color.White,
                                inactiveTrackColor = Color.White.copy(alpha = 0.3f)
                            )
                        )
                        Text(formatDuration(duration), color = Color.White, fontSize = 14.sp)
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = onBack) {
                            Icon(Icons.Default.ArrowBack, null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("رجوع", color = Color.White, fontSize = 13.sp)
                        }

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(Color.White, CircleShape)
                                .clickable {
                                    if (exoPlayer.isPlaying) exoPlayer.pause()
                                    else exoPlayer.play()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                if (exoPlayer.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                null,
                                tint = Color.Black,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        TextButton(onClick = { exoPlayer.seekTo(0) }) {
                            Icon(Icons.Default.Refresh, null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("ابدأ من جديد", color = Color.White, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}

private fun formatDuration(ms: Long): String {
    val totalSec = ms / 1000
    val h = totalSec / 3600
    val m = (totalSec % 3600) / 60
    val s = totalSec % 60
    return if (h > 0) "%d:%02d:%02d".format(h, m, s) else "%02d:%02d".format(m, s)
}
