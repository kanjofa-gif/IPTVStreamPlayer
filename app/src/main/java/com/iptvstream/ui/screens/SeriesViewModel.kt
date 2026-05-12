package com.iptvstream.ui.screens.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.*
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SeriesState(
    val series: List<Series> = emptyList(),
    val categories: List<SeriesCategory> = emptyList(),
    val selectedCategoryId: String? = null,
    val isLoading: Boolean = true,
    val selectedSeries: Series? = null,
    val seriesInfo: SeriesInfo? = null,
    val isLoadingInfo: Boolean = false,
    val playlist: Playlist? = null
) {
    val filteredSeries: List<Series>
        get() = when (selectedCategoryId) {
            null, "all" -> series
            "favorites" -> series
            else -> series.filter { it.category_id == selectedCategoryId }
        }
}

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SeriesState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val playlist = repository.getSelectedPlaylist() ?: return@launch
            _state.value = _state.value.copy(isLoading = true, playlist = playlist)

            repository.getSeriesCategories(playlist).onSuccess { cats ->
                _state.value = _state.value.copy(categories = cats)
            }

            repository.getSeries(playlist).onSuccess { series ->
                _state.value = _state.value.copy(
                    series = series.sortedByDescending { it.last_modified },
                    isLoading = false
                )
            }
        }
    }

    fun selectCategory(categoryId: String) {
        _state.value = _state.value.copy(selectedCategoryId = categoryId)
    }

    fun selectSeries(series: Series) {
        _state.value = _state.value.copy(selectedSeries = series, isLoadingInfo = true)
        viewModelScope.launch {
            val playlist = _state.value.playlist ?: return@launch
            repository.getSeriesInfo(playlist, series.series_id).onSuccess { info ->
                _state.value = _state.value.copy(seriesInfo = info, isLoadingInfo = false)
            }
        }
    }

    fun dismissDetail() {
        _state.value = _state.value.copy(selectedSeries = null, seriesInfo = null)
    }

    fun episodeUrl(episodeId: String, ext: String): String {
        val playlist = _state.value.playlist ?: return ""
        val base = playlist.url.trimEnd('/')
        return "$base/series/${playlist.username}/${playlist.password}/$episodeId.$ext"
    }
}
