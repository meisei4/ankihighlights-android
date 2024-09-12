package com.ankihighlights.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logIntentText(intent) // Print the intent text if available

        setContent {
            HighlightScreen(intent = intent) // Pass the intent to the composable
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("MainActivity", "New intent received with action: ${intent.action}")
        logIntentText(intent) // Print the intent text when a new intent is received

        setContent {
            HighlightScreen(intent = intent) // Pass the intent to the composable
        }
    }

    // Function to print the highlighted text from the intent
    private fun logIntentText(intent: Intent?) {
        val highlightedText = intent?.getStringExtra(Intent.EXTRA_PROCESS_TEXT)
        if (!highlightedText.isNullOrBlank()) {
            Log.d("MainActivity", "Highlighted text: $highlightedText")
        } else {
            Log.d("MainActivity", "No valid highlighted text in the intent")
        }
    }
}
