package com.iptvstream.ui.screens.live

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.*
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val password: String = ""
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

            _state.value = _state.value.copy(
                categories = categories,
                allStreams = streams,
                filteredStreams = streams,
                isLoading = false
            )
        }
    }

    fun selectCategory(categoryId: String) {
        val s = _state.value
        _state.value = s.copy(selectedCategoryId = categoryId)
        filterStreams()
    }

    fun onSearch(query: String) {
        _state.value = _state.value.copy(searchQuery = query)
        filterStreams()
    }

    private fun filterStreams() {
        val s = _state.value
        val filtered = s.allStreams
            .filter { stream ->
                (s.selectedCategoryId.isEmpty() || s.selectedCategoryId == "favorites" ||
                        stream.category_id == s.selectedCategoryId)
            }
            .filter { stream ->
                s.searchQuery.isEmpty() || stream.name.contains(s.searchQuery, ignoreCase = true)
            }
        _state.value = _state.value.copy(filteredStreams = filtered)
    }
}
