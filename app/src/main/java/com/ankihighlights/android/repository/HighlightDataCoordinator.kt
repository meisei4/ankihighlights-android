package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.model.cache.HighlightCacheEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HighlightDataCoordinator
    @Inject
    constructor(
        private val networkRepo: NetworkHighlightRepository,
        private val localRepo: LocalHighlightRepository,
    ) {
        fun processHighlights(highlightData: HighlightData): Flow<HighlightResponse> {
            // Process highlights using the network repository
            return networkRepo.processHighlights(highlightData)
        }

        suspend fun cacheHighlightLocally(highlightText: String) {
            // Cache the highlight in the local DB
            localRepo.cacheHighlight(highlightText)
        }

        fun getCachedHighlights(): Flow<List<HighlightCacheEntity>> {
            // Get cached highlights from local DB
            return localRepo.getCachedHighlights()
        }
    }
