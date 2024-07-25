package com.ankihighlights.android.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class HighlightDTO(
    val id: Int,
    val word: String,
    val context: String,
    val timestamp: Long
)
