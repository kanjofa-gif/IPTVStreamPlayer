package com.iptvstream.ui.screens.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.Playlist
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ManagePlaylistsState(
    val playlists: List<Playlist> = emptyList(),
    val showDialog: Boolean = false,
    val editingPlaylist: Playlist? = null
)

@HiltViewModel
class ManagePlaylistsViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ManagePlaylistsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPlaylists().collectLatest { playlists ->
                _state.value = _state.value.copy(playlists = playlists)
            }
        }
    }

    fun selectPlaylist(playlist: Playlist) {
        viewModelScope.launch { repository.selectPlaylist(playlist.id) }
    }

    fun deletePlaylist(playlist: Playlist) {
        viewModelScope.launch { repository.deletePlaylist(playlist) }
    }

    fun editPlaylist(playlist: Playlist) {
        _state.value = _state.value.copy(showDialog = true, editingPlaylist = playlist)
    }

    fun showAddDialog() {
        _state.value = _state.value.copy(showDialog = true, editingPlaylist = null)
    }

    fun hideDialog() {
        _state.value = _state.value.copy(showDialog = false, editingPlaylist = null)
    }

    fun savePlaylist(name: String, url: String, username: String, password: String) {
        viewModelScope.launch {
            val editing = _state.value.editingPlaylist
            if (editing != null) {
                repository.insertPlaylist(editing.copy(name = name, url = url, username = username, password = password))
            } else {
                repository.insertPlaylist(Playlist(name = name.ifBlank { url }, url = url, username = username, password = password))
            }
            hideDialog()
        }
    }
}
