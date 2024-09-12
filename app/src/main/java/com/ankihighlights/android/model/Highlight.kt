package com.ankihighlights.android.model

import kotlinx.serialization.Serializable

@Serializable
data class HighlightData(
    val word: String,
    val context: String,
    val timestamp: Long,
)
