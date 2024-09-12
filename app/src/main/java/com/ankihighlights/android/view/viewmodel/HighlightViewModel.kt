package com.ankihighlights.android.view.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankihighlights.android.repository.HighlightDataCoordinator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel
    @Inject
    constructor(
        private val coordinator: HighlightDataCoordinator,
    ) : ViewModel() {
        private val _cachedHighlights = MutableStateFlow<List<String>>(emptyList())
        val cachedHighlights: StateFlow<List<String>> = _cachedHighlights.asStateFlow()

        init {
            viewModelScope.launch {
                coordinator.getCachedHighlights().collect { cachedHighlights ->
                    _cachedHighlights.value = cachedHighlights.map { it.highlightedText }
                }
            }
        }

        fun processIncomingIntent(intent: Intent?) {
            intent?.getStringExtra(Intent.EXTRA_PROCESS_TEXT)?.let { highlightedText ->
                if (highlightedText.isNotBlank()) {
                    Log.d("HighlightViewModel", "Processing highlighted text: $highlightedText")
                    viewModelScope.launch {
                        coordinator.cacheHighlightLocally(highlightedText)
                    }
                } else {
                    Log.d("HighlightViewModel", "Received blank or invalid highlighted text")
                }
            } ?: Log.d("HighlightViewModel", "Intent does not contain EXTRA_PROCESS_TEXT")
        }
    }
