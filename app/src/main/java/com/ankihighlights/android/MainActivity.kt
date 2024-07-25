package com.ankihighlights.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.ankihighlights.android.view.viewmodel.HighlightViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val highlightViewModel: HighlightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        highlightViewModel.startListeningForHighlights()
    }
}
