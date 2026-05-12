package com.iptvstream.ui.screens.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.*
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LiveState(
    val categories: List<LiveCategory> = emptyList(),
    val allStreams: List<LiveStream> = emptyList(),
    val filteredStreams: List<LiveStream> = emptyList(),
    val selectedCategoryId: String = "",
    val searchQuery: String = "",
    val isLoading: Boolean = true,
    val baseUrl: String = "",
    val username: String = "",
    val password: String = "",
    val favoriteIds: Set<String> = emptySet()
) {
    fun streamUrl(streamId: Int) = if (baseUrl.isNotBlank())
        "${baseUrl.trimEnd('/')}/live/$username/$password/$streamId.ts" else ""
}

@HiltViewModel
class LiveViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LiveState())
    val state = _state.asStateFlow()

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            val playlist = repository.getSelectedPlaylist() ?: return@launch
            _state.value = _state.value.copy(
                isLoading = true,
                baseUrl = playlist.url,
                username = playlist.username,
                password = playlist.password
            )

            val categories = repository.getLiveCategories(playlist).getOrDefault(emptyList())
            val streams = repository.getLiveStreams(playlist).getOrDefault(emptyList())

            val favorites = try {
                repository.getFavoritesByType("live").first().map { it.streamId }.toSet()
            } catch (e: Exception) { emptySet() }

            _state.value = _state.value.copy(
                categories = categories,
                allStreams = streams,
                filteredStreams = streams,
                favoriteIds = favorites,
                isLoading = false
            )
        }
    }

    fun selectCategory(categoryId: String) {
        _state.value = _state.value.copy(selectedCategoryId = categoryId)
        filterStreams()
    }

    fun onSearch(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        filterStreams()
    }

    private fun filterStreams() {
        val s = _state.value
        val byCategory = when (s.selectedCategoryId) {
            "" -> s.allStreams
            "favorites" -> s.allStreams.filter { it.stream_id.toString() in s.favoriteIds }
            else -> s.allStreams.filter { it.category_id == s.selectedCategoryId }
        }
        val filtered = byCategory.filter {
            s.searchQuery.isEmpty() || it.name.contains(s.searchQuery, ignoreCase = true)
        }
        _state.value = _state.value.copy(filteredStreams = filtered)
    }

    fun toggleFavorite(stream: LiveStream) {
        viewModelScope.launch {
            val id = stream.stream_id.toString()
            if (id in _state.value.favoriteIds) {
                repository.removeFavorite(id)
                _state.value = _state.value.copy(favoriteIds = _state.value.favoriteIds - id)
            } else {
                repository.addFavorite(Favorite(
                    streamId = id,
                    streamName = stream.name,
                    streamIcon = stream.stream_icon,
                    streamType = "live",
                    categoryId = stream.category_id
                ))
                _state.value = _state.value.copy(favoriteIds = _state.value.favoriteIds + id)
            }
            filterStreams()
        }
    }
}
