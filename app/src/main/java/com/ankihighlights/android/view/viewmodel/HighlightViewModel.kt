package com.ankihighlights.android.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor() : ViewModel() {

    fun startListeningForHighlights() {
        viewModelScope.launch {
            while (true) {
                // Placeholder for highlight event listening logic
                delay(5000) // Wait for 5 seconds before checking again
            }
        }
    }
}
