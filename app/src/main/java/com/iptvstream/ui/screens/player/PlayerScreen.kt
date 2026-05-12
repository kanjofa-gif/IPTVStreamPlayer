package com.iptvstream.ui.screens.player

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

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
    val context = androidx.compose.ui.platform.LocalContext.current
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Log.d("SHooF", "PlayerScreen: type=$type id=$id url=$url title=$title")

    if (url.isBlank()) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("الرابط فارغ", color = Color.White, fontSize = 18.sp)
                Spacer(Modifier.height(16.dp))
                Button(onClick = onBack) { Text("رجوع") }
            }
        }
        return
    }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    errorMessage = "خطأ ${error.errorCode}: ${error.message}"
                    Log.e("SHooF", "Player error", error)
                }
            })
        }
    }

    LaunchedEffect(url) {
        try {
            exoPlayer.setMediaItem(MediaItem.fromUri(url))
            exoPlayer.prepare()
        } catch (e: Exception) {
            errorMessage = "فشل التحميل: ${e.message}"
            Log.e("SHooF", "setMediaItem failed", e)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                if (exoPlayer.currentPosition > 0) {
                    viewModel.saveProgress(
                        id = id, name = title, icon = icon, url = url, type = type,
                        position = exoPlayer.currentPosition,
                        duration = exoPlayer.duration.takeIf { it > 0 } ?: 0
                    )
                }
                exoPlayer.release()
            } catch (e: Exception) { }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = true
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        if (errorMessage != null) {
            Column(
                modifier = Modifier.align(Alignment.Center).padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("خطأ في التشغيل", color = Color.White, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))
                Text(errorMessage!!, color = Color.Gray, fontSize = 13.sp)
                Spacer(Modifier.height(8.dp))
                Text(url, color = Color.Gray, fontSize = 10.sp)
                Spacer(Modifier.height(16.dp))
                Button(onClick = onBack) { Text("رجوع") }
            }
        }

        IconButton(
            onClick = onBack,
            modifier = Modifier.align(Alignment.TopStart).padding(16.dp)
        ) {
            Text("←", color = Color.White, fontSize = 28.sp)
        }
    }
}
