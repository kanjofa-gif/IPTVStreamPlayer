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
    val savedPosition: Long = 0L,
    val duration: Long = 0L
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    fun loadProgress(id: String) {
        viewModelScope.launch {
            val progress = repository.getProgress(id)
            _state.value = _state.value.copy(
                savedPosition = progress?.positionMs ?: 0L,
                duration = progress?.durationMs ?: 0L
            )
        }
    }

    fun getSavedPosition() = _state.value.savedPosition

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
