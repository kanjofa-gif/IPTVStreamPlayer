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
    val isLoading: Boolean = true
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    fun loadStream(type: String, id: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val playlist = repository.getSelectedPlaylist() ?: return@launch

            val url = when (type) {
                "live" -> repository.buildStreamUrl(playlist, id.toIntOrNull() ?: 0)
                "movie" -> {
                    val streams = repository.getVodStreams(playlist).getOrDefault(emptyList())
                    val stream = streams.find { it.stream_id == id.toIntOrNull() }
                    if (stream != null)
                        repository.buildVodUrl(playlist, stream.stream_id, stream.container_extension)
                    else ""
                }
                else -> ""
            }

            val name = when (type) {
                "live" -> {
                    val streams = repository.getLiveStreams(playlist).getOrDefault(emptyList())
                    streams.find { it.stream_id == id.toIntOrNull() }?.name ?: ""
                }
                "movie" -> {
                    val streams = repository.getVodStreams(playlist).getOrDefault(emptyList())
                    streams.find { it.stream_id == id.toIntOrNull() }?.name ?: ""
                }
                else -> ""
            }

            val progress = repository.getProgress(id)

            _state.value = _state.value.copy(
                streamUrl = url,
                streamTitle = name,
                savedPosition = progress?.positionMs ?: 0L,
                isLoading = false
            )
        }
    }

    fun saveProgress(id: String, name: String, icon: String, url: String, type: String, position: Long, duration: Long) {
        if (position <= 0) return
        viewModelScope.launch {
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
        }
    }
}
