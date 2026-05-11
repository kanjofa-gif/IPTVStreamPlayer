package com.iptvstream.ui.screens.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.WatchProgress
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    fun saveProgress(
        id: String,
        name: String,
        icon: String,
        url: String,
        type: String,
        position: Long,
        duration: Long
    ) {
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
