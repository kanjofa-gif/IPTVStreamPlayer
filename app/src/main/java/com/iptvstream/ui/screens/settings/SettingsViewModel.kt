package com.iptvstream.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsState(
    val autoNextEpisode: Boolean = true,
    val watchFromMin: Int = 0,
    val watchToMin: Int = 5,
    val parentalPin: String? = null,
    val appId: String = "",
    val showPinDialog: Boolean = false
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAutoNextEpisode(),
                repository.getWatchFromMin(),
                repository.getWatchToMin(),
                repository.getParentalPin(),
                repository.getAppId()
            ) { autoNext, fromMin, toMin, pin, appId ->
                SettingsState(
                    autoNextEpisode = autoNext,
                    watchFromMin = fromMin,
                    watchToMin = toMin,
                    parentalPin = pin,
                    appId = appId
                )
            }.collectLatest { _state.value = it }
        }
    }

    fun setAutoNextEpisode(value: Boolean) {
        viewModelScope.launch { repository.setAutoNextEpisode(value) }
    }

    fun setWatchFromMin(value: Int) {
        if (value < 0) return
        viewModelScope.launch { repository.setWatchFromMin(value) }
    }

    fun setWatchToMin(value: Int) {
        if (value < 0) return
        viewModelScope.launch { repository.setWatchToMin(value) }
    }

    fun showPinDialog() { _state.value = _state.value.copy(showPinDialog = true) }
    fun hidePinDialog() { _state.value = _state.value.copy(showPinDialog = false) }

    fun setPin(pin: String?) {
        viewModelScope.launch {
            repository.setParentalPin(pin)
            hidePinDialog()
        }
    }
}
