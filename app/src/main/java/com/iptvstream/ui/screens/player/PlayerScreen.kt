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
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState())
    val state = _state.asStateFlow()

    fun setUrl(url: String, title: String = "", icon: String = "") {
        _state.value = _state.value.copy(
            streamUrl = url,
            streamTitle = title,
            streamIcon = icon,
            isLoading = false
        )
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
