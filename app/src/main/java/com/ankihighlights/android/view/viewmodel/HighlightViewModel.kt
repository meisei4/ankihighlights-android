package com.ankihighlights.android.view.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankihighlights.android.repository.HighlightDataRepository
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
        private val repository: HighlightDataRepository,
    ) : ViewModel() {
        private val _cachedHighlights = MutableStateFlow<List<String>>(emptyList())
        val cachedHighlights: StateFlow<List<String>> = _cachedHighlights.asStateFlow()

        init {
            // Load cached highlights from Room on initialization
            viewModelScope.launch {
                repository.getCachedHighlights().collect { cachedHighlights ->
                    _cachedHighlights.value = cachedHighlights.map { it.highlightedText }
                }
            }
        }

        fun processIncomingIntent(intent: Intent?) {
            val highlightedText = intent?.getStringExtra(Intent.EXTRA_PROCESS_TEXT)
            if (highlightedText != null) {
                viewModelScope.launch {
                    // Cache the highlighted string in Room
                    repository.cacheHighlight(highlightedText)
                }
            }
        }
    }
