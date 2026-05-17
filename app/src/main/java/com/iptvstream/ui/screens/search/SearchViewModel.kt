package com.iptvstream.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.*
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class SearchTab(val label: String) {
    ALL("الكل"),
    LIVE("القنوات"),
    MOVIES("الأفلام"),
    SERIES("المسلسلات")
}

data class SearchState(
    val query: String = "",
    val selectedTab: SearchTab = SearchTab.ALL,
    val liveResults: List<LiveStream> = emptyList(),
    val movieResults: List<VodStream> = emptyList(),
    val seriesResults: List<Series> = emptyList(),
    val isLoading: Boolean = false,
    val playlist: Playlist? = null,
    private val allLive: List<LiveStream> = emptyList(),
    private val allMovies: List<VodStream> = emptyList(),
    private val allSeries: List<Series> = emptyList()
) {
    val baseUrl: String get() = playlist?.url ?: ""
    val username: String get() = playlist?.username ?: ""
    val password: String get() = playlist?.password ?: ""

    fun liveUrl(id: Int) = "${baseUrl.trimEnd('/')}/live/$username/$password/$id.ts"
    fun movieUrl(id: Int, ext: String) = "${baseUrl.trimEnd('/')}/movie/$username/$password/$id.$ext"

    val totalResults: Int get() = liveResults.size + movieResults.size + seriesResults.size
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private var allLive: List<LiveStream> = emptyList()
    private var allMovies: List<VodStream> = emptyList()
    private var allSeries: List<Series> = emptyList()

    init { loadData() }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val playlist = repository.getSelectedPlaylist() ?: run {
                _state.value = _state.value.copy(isLoading = false)
                return@launch
            }

            allLive = repository.getLiveStreams(playlist).getOrDefault(emptyList())
            allMovies = repository.getVodStreams(playlist).getOrDefault(emptyList())
            allSeries = repository.getSeries(playlist).getOrDefault(emptyList())

            _state.value = _state.value.copy(
                playlist = playlist,
                isLoading = false
            )
        }
    }

    fun updateQuery(query: String) {
        _state.value = _state.value.copy(query = query)
        performSearch()
    }

    fun selectTab(tab: SearchTab) {
        _state.value = _state.value.copy(selectedTab = tab)
    }

    private fun performSearch() {
        val q = _state.value.query.trim()
        if (q.isEmpty()) {
            _state.value = _state.value.copy(
                liveResults = emptyList(),
                movieResults = emptyList(),
                seriesResults = emptyList()
            )
            return
        }

        val lowerQ = q.lowercase()

        val liveMatches = allLive.filter {
            it.name.lowercase().contains(lowerQ)
        }.take(50)

        val movieMatches = allMovies.filter {
            it.name.lowercase().contains(lowerQ)
        }.take(50)

        val seriesMatches = allSeries.filter {
            it.name.lowercase().contains(lowerQ)
        }.take(50)

        _state.value = _state.value.copy(
            liveResults = liveMatches,
            movieResults = movieMatches,
            seriesResults = seriesMatches
        )
    }
}
