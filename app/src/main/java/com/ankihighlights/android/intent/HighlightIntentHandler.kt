package com.ankihighlights.android.intent

import android.content.Intent
import javax.inject.Inject

class HighlightIntentHandler
    @Inject
    constructor() {
        fun handleIntent(intent: Intent?): String? {
            return if (intent?.action == Intent.ACTION_PROCESS_TEXT && intent.hasExtra(Intent.EXTRA_PROCESS_TEXT)) {
                intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT)
            } else {
                null
            }
        }
    }
