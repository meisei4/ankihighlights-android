package com.ankihighlights.android.repository

import androidx.lifecycle.LiveData
import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse

interface HighlightRepository {
    fun processHighlights(highlightData: HighlightData): LiveData<HighlightResponse>
}
