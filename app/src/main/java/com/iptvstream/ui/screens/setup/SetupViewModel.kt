package com.iptvstream.ui.screens.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.model.Playlist
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SetupState(
    val url: String = "",
    val name: String = "",
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val navigateToHome: Boolean = false
)

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SetupState())
    val state = _state.asStateFlow()

    fun onUrlChanged(url: String) = _state.value.let { _state.value = it.copy(url = url, error = null) }
    fun onNameChanged(name: String) = _state.value.let { _state.value = it.copy(name = name) }
    fun onUsernameChanged(u: String) = _state.value.let { _state.value = it.copy(username = u) }
    fun onPasswordChanged(p: String) = _state.value.let { _state.value = it.copy(password = p) }

    fun addPlaylist() {
        val s = _state.value
        if (s.url.isBlank()) return

        _state.value = s.copy(isLoading = true, error = null)

        viewModelScope.launch {
            val playlist = Playlist(
                name = s.name.ifBlank { extractDomain(s.url) },
                url = s.url.trim(),
                username = s.username.trim(),
                password = s.password.trim()
            )

            val result = repository.authenticate(playlist)
            result.fold(
                onSuccess = {
                    repository.insertPlaylist(playlist.copy(isSelected = true))
                    _state.value = _state.value.copy(isLoading = false, navigateToHome = true)
                },
                onFailure = { e ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        error = "فشل الاتصال: تحقق من البيانات"
                    )
                }
            )
        }
    }

    private fun extractDomain(url: String): String {
        return try {
            java.net.URI(url).host ?: url
        } catch (e: Exception) {
            url
        }
    }
}
