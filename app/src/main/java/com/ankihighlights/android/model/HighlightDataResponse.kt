package com.ankihighlights.android.model

import com.ankihighlights.android.model.dto.HighlightDTO
import kotlinx.serialization.Serializable

@Serializable
data class HighlightResponse(
    val success: Boolean,
    val data: HighlightDataWrapper?,
    val message: String?
)

// TODO: remove this once HighlightResponse is testable as the fundamental endpoint "touch" test or whatever
@Serializable
data class TestResponse(val success: Boolean, val message: String)

// TODO: this just wraps the DTO in  a list???? Figure out why this would be necessary as a Serializable or not...
@Serializable
data class HighlightDataWrapper(
    val highlight: List<HighlightDTO>
)

