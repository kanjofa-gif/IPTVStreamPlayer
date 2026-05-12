package com.iptvstream.ui.screens.movies

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

data class MoviesState(
    val categories: List<VodCategory> = emptyList(),
    val allMovies: List<VodStream> = emptyList(),
    val filteredMovies: List<VodStream> = emptyList(),
    val selectedCategoryId: String = "latest",
    val selectedMovie: VodStream? = null,
    val watchProgress: Long? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = true,
    val baseUrl: String = "",
    val username: String = "",
    val password: String = "",
    val favoriteIds: Set<String> = emptySet(),
    val progressIds: Set<String> = emptySet()
) {
    fun movieUrl(streamId: Int, ext: String) =
        "${baseUrl.trimEnd('/')}/movie/$username/$password/$streamId.$ext"
}

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
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

            val categories = repository.getVodCategories(playlist).getOrDefault(emptyList())
            val movies = repository.getVodStreams(playlist).getOrDefault(emptyList())
            val sorted = movies.sortedByDescending { it.added }

            val favorites = try {
                repository.getFavoritesByType("movie").first().map { it.streamId }.toSet()
            } catch (e: Exception) { emptySet() }

            val progress = try {
                repository.getRecentlyWatched().first()
                    .filter { it.streamType == "movie" }
                    .map { it.streamId }
                    .toSet()
            } catch (e: Exception) { emptySet() }

            _state.value = _state.value.copy(
                categories = categories,
                allMovies = sorted,
                filteredMovies = sorted,
                favoriteIds = favorites,
                progressIds = progress,
                isLoading = false
            )
        }
    }

    fun selectCategory(categoryId: String) {
        val s = _state.value
        val filtered = when (categoryId) {
            "latest" -> s.allMovies
            "favorites" -> s.allMovies.filter { it.stream_id.toString() in s.favoriteIds }
            "continue" -> s.allMovies.filter { it.stream_id.toString() in s.progressIds }
            else -> s.allMovies.filter { it.category_id == categoryId }
        }
        _state.value = s.copy(selectedCategoryId = categoryId, filteredMovies = filtered)
    }

    fun selectMovie(movie: VodStream) {
        viewModelScope.launch {
            val progress = repository.getProgress(movie.stream_id.toString())
            val fav = repository.getFavorite(movie.stream_id.toString())
            _state.value = _state.value.copy(
                selectedMovie = movie,
                watchProgress = progress?.positionMs,
                isFavorite = fav != null
            )
        }
    }

    fun dismissDetail() {
        _state.value = _state.value.copy(selectedMovie = null, watchProgress = null, isFavorite = false)
    }

    fun toggleFavorite() {
        val movie = _state.value.selectedMovie ?: return
        viewModelScope.launch {
            val id = movie.stream_id.toString()
            if (_state.value.isFavorite) {
                repository.removeFavorite(id)
                _state.value = _state.value.copy(
                    isFavorite = false,
                    favoriteIds = _state.value.favoriteIds - id
                )
            } else {
                repository.addFavorite(Favorite(
                    streamId = id,
                    streamName = movie.name,
                    streamIcon = movie.stream_icon,
                    streamType = "movie",
                    categoryId = movie.category_id
                ))
                _state.value = _state.value.copy(
                    isFavorite = true,
                    favoriteIds = _state.value.favoriteIds + id
                )
            }
        }
    }

    fun removeProgress() {
        val movie = _state.value.selectedMovie ?: return
        viewModelScope.launch {
            repository.deleteProgress(movie.stream_id.toString())
            _state.value = _state.value.copy(watchProgress = null)
        }
    }
}
