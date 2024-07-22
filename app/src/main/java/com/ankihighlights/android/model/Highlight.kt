package com.ankihighlights.android.model


data class HighlightData(
    val word: String,
    val context: String,
    val timestamp: Long
)

// Response data models
data class HighlightResponse(
    val success: Boolean,
    val data: HighlightDataWrapper?,
    val message: String?
)

data class HighlightDataWrapper(
    val highlight: List<HighlightDTO>
)

data class HighlightDTO(
    val id: Int,
    val word: String,
    val context: String,
    val timestamp: Long
)
