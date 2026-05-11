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
                    _state.value = _state.value.copy(isLoading = false, error = "لا توجد قائمة")
                    return@launch
                }

                val streamId = id.toIntOrNull() ?: 0
                val base = playlist.url.trimEnd('/')
                val user = playlist.username
                val pass = playlist.password

                val url = when (type) {
                    "live" -> "$base/live/$user/$pass/$streamId.ts"
                    "movie" -> "$base/movie/$user/$pass/$streamId.mkv"
                    "series" -> "$base/series/$user/$pass/$streamId.mkv"
                    else -> ""
                }

                _state.value = _state.value.copy(
                    streamUrl = url,
                    streamTitle = "",
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
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
