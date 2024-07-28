package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import kotlinx.coroutines.flow.Flow

fun interface NetworkHighlightRepository {
    fun processHighlights(highlightData: HighlightData): Flow<HighlightResponse>
}
