package com.ankihighlights.android.repository

import com.ankihighlights.android.model.cache.HighlightCacheEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeLocalHighlightRepository
    @Inject
    constructor() : LocalHighlightRepository {
        // In-memory cache for highlights
        private val highlightCache = mutableListOf<HighlightCacheEntity>()

        // StateFlow to emit the list of highlights
        private val _cachedHighlights = MutableStateFlow<List<HighlightCacheEntity>>(emptyList())
        private val cachedHighlights = _cachedHighlights.asStateFlow()

        override suspend fun cacheHighlight(highlightText: String) {
            // Create a new HighlightCacheEntity
            val newHighlight =
                HighlightCacheEntity(
                    highlightedText = highlightText,
                    timestamp = System.currentTimeMillis(),
                )

            // Add to in-memory cache
            highlightCache.add(newHighlight)

            // Update the Flow with the new list
            _cachedHighlights.value = highlightCache.toList()
        }

        override fun getCachedHighlights(): Flow<List<HighlightCacheEntity>> {
            // Return the flow of cached highlights
            return cachedHighlights
        }
    }
