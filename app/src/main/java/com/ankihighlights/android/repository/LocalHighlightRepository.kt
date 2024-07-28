package com.ankihighlights.android.repository

import com.ankihighlights.android.model.cache.HighlightCacheEntity
import kotlinx.coroutines.flow.Flow

interface LocalHighlightRepository {
    suspend fun cacheHighlight(highlightText: String)

    fun getCachedHighlights(): Flow<List<HighlightCacheEntity>>
}
