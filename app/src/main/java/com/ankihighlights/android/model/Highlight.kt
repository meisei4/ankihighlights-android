package com.ankihighlights.android.model

import kotlinx.serialization.Serializable

@Serializable
data class HighlightData(
    val word: String,
    val context: String,
    val timestamp: Long
)

@Serializable
data class HighlightResponse(
    val success: Boolean,
    val data: HighlightDataWrapper?,
    val message: String?
)

@Serializable
data class HighlightDataWrapper(
    val highlight: List<HighlightDTO>
)

@Serializable
data class HighlightDTO(
    val id: Int,
    val word: String,
    val context: String,
    val timestamp: Long
)

@Serializable
data class TestResponse(val success: Boolean, val message: String)