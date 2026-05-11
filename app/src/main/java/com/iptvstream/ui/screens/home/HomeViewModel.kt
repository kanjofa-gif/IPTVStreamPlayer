package com.iptvstream.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.*
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeState(
    val recentLive: List<WatchProgress> = emptyList(),
    val latestMovies: List<VodStream> = emptyList(),
    val latestSeries: List<Series> = emptyList(),
    val userInfo: UserInfo? = null,
    val expDate: String = "",
    val maxConnections: String = "0",
    val isTrial: Boolean = false,
    val isDrawerOpen: Boolean = false,
    val isLoading: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        observeRecentLive()
        loadContent()
    }

    private fun observeRecentLive() {
        viewModelScope.launch {
            repository.getRecentLive().collectLatest { recent ->
                _state.value = _state.value.copy(recentLive = recent)
            }
        }
    }

    private fun loadContent() {
        viewModelScope.launch {
            val playlist = repository.getSelectedPlaylist() ?: return@launch

            // Load user info
            repository.authenticate(playlist).onSuccess { auth ->
                val ui = auth.user_info
                val expDateFormatted = ui.exp_date?.let {
                    try {
                        val sdf = java.text.SimpleDateFormat("dd MMMM yyyy", java.util.Locale("ar"))
                        sdf.format(java.util.Date(it.toLong() * 1000))
                    } catch (e: Exception) { it }
                } ?: ""
                _state.value = _state.value.copy(
                    userInfo = ui,
                    expDate = expDateFormatted,
                    maxConnections = ui.max_connections,
                    isTrial = ui.is_trial == "1"
                )
            }

            // Load latest movies (last 20)
            repository.getVodStreams(playlist).onSuccess { movies ->
                _state.value = _state.value.copy(
                    latestMovies = movies.sortedByDescending { it.added }.take(20)
                )
            }

            // Load latest series (last 20)
            repository.getSeries(playlist).onSuccess { series ->
                _state.value = _state.value.copy(
                    latestSeries = series.sortedByDescending { it.last_modified }.take(20)
                )
            }
        }
    }

    fun openDrawer() { _state.value = _state.value.copy(isDrawerOpen = true) }
    fun closeDrawer() { _state.value = _state.value.copy(isDrawerOpen = false) }

    fun refreshData() {
        viewModelScope.launch {
            closeDrawer()
            loadContent()
        }
    }
}
