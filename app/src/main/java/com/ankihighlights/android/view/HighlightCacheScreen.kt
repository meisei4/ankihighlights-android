package com.ankihighlights.android.view

import android.content.Intent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ankihighlights.android.view.viewmodel.HighlightViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HighlightCacheScreen(
    intent: Intent? = null, // Pass the Intent if available
    viewModel: HighlightViewModel = hiltViewModel(), // Obtain ViewModel using Hilt
) {
    // Process the incoming intent in the ViewModel
    LaunchedEffect(intent) {
        intent?.let {
            viewModel.processIncomingIntent(it) // Process the intent
        }
    }

    // Collect the cached highlights from the ViewModel as state
    val cachedHighlights = viewModel.cachedHighlights.collectAsState()

    // Display the cached highlights in a LazyColumn
    LazyColumn {
        items(cachedHighlights.value) { highlight ->
            Text(text = highlight) // Render each highlight string
        }
    }
}
