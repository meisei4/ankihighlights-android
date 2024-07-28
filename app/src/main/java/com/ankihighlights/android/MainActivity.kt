package com.ankihighlights.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ankihighlights.android.view.HighlightCacheScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HighlightCacheScreen()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setContent {
            HighlightCacheScreen(intent = intent)
        }
    }
}
