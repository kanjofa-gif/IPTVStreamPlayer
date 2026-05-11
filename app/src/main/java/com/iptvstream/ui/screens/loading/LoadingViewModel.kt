package com.iptvstream.ui.screens.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iptvstream.data.repository.IPTVRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoadingState(
    val progress: Float = 0f,
    val currentStep: String = "جاري الاتصال...",
    val isComplete: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class LoadingViewModel @Inject constructor(
    private val repository: IPTVRepository
) : ViewModel() {

    private val _state = MutableStateFlow(LoadingState())
    val state = _state.asStateFlow()

    init { loadData() }

    fun loadData() {
        viewModelScope.launch {
            _state.value = LoadingState(progress = 0f, currentStep = "جاري الاتصال بالمشغّل...")

            val playlist = repository.getSelectedPlaylist()
            if (playlist == null) {
                _state.value = _state.value.copy(error = "لا توجد قائمة تشغيل محددة")
                return@launch
            }

            delay(300)
            _state.value = _state.value.copy(progress = 0.2f, currentStep = "جاري تحميل القنوات المباشرة...")
            repository.getLiveCategories(playlist)
            repository.getLiveStreams(playlist)

            delay(200)
            _state.value = _state.value.copy(progress = 0.5f, currentStep = "جاري تحميل الأفلام...")
            repository.getVodCategories(playlist)
            repository.getVodStreams(playlist)

            delay(200)
            _state.value = _state.value.copy(progress = 0.75f, currentStep = "جاري تحميل المسلسلات...")
            repository.getSeriesCategories(playlist)
            repository.getSeries(playlist)

            delay(300)
            _state.value = _state.value.copy(progress = 1f, currentStep = "اكتمل التحميل!")
            delay(500)
            _state.value = _state.value.copy(isComplete = true)
        }
    }
}
