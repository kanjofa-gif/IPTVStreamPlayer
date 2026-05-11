package com.iptvstream.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.WatchProgress
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PlayerState(
    val streamUrl: String = "",
    val streamTitle: String = "",
    val streamIcon: String = "",
    val savedPosition: Long = 0L,
    val duration: Long = 0L,
    val isLoading: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    fun loadStream(type: String, id: String) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true, error = null)
                val playlist = repository.getSelectedPlaylist() ?: run {
                    _state.value = _state.value.copy(isLoading = false, error = "لا توجد قائمة تشغيل")
                    return@launch
                }

                val streamId = id.toIntOrNull() ?: 0

                val url = when (type) {
                    "live" -> repository.buildStreamUrl(playlist, streamId)
                    "movie" -> repository.buildVodUrl(playlist, streamId, "mkv")
                    "series" -> repository.buildSeriesUrl(playlist, streamId, "mkv")
                    else -> ""
                }

                val name = when (type) {
                    "live" -> {
                        try {
                            val streams = repository.getLiveStreams(playlist).getOrDefault(emptyList())
                            streams.find { it.stream_id == streamId }?.name ?: ""
                        } catch (e: Exception) { "" }
                    }
                    "movie" -> {
                        try {
                            val streams = repository.getVodStreams(playlist).getOrDefault(emptyList())
                            streams.find { it.stream_id == streamId }?.name ?: ""
                        } catch (e: Exception) { "" }
                    }
                    else -> ""
                }

                val progress = try { repository.getProgress(id) } catch (e: Exception) { null }

                _state.value = _state.value.copy(
                    streamUrl = url,
                    streamTitle = name,
                    savedPosition = progress?.positionMs ?: 0L,
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message ?: "خطأ غير معروف"
                )
            }
        }
    }

    fun saveProgress(id: String, name: String, icon: String, url: String, type: String, position: Long, duration: Long) {
        if (position <= 0) return
        viewModelScope.launch {
            try {
                repository.saveProgress(
                    WatchProgress(
                        streamId = id,
                        streamName = name,
                        streamIcon = icon,
                        streamUrl = url,
                        streamType = type,
                        positionMs = position,
                        durationMs = duration
                    )
                )
            } catch (e: Exception) { }
        }
    }
}
