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
    intent: Intent? = null,
    viewModel: HighlightViewModel = hiltViewModel(),
) {
    LaunchedEffect(intent) {
        intent?.let {
            viewModel.processIncomingIntent(it)
        }
    }

    val cachedHighlights = viewModel.cachedHighlights.collectAsState()

    LazyColumn {
        items(cachedHighlights.value) { highlight ->
            Text(text = highlight)
        }
    }
}
