package com.ankihighlights.android.repository

import com.ankihighlights.android.model.cache.HighlightCacheEntity
import com.ankihighlights.android.model.cache.HighlightDAO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalHighlightDataRepository
    @Inject
    constructor(
        private val highlightDao: HighlightDAO,
    ) : LocalHighlightRepository {
        override suspend fun cacheHighlight(highlightText: String) {
            highlightDao.insertHighlight(
                HighlightCacheEntity(
                    highlightedText = highlightText,
                    timestamp = System.currentTimeMillis(),
                ),
            )
        }

        override fun getCachedHighlights(): Flow<List<HighlightCacheEntity>> {
            return highlightDao.getRecentHighlights()
        }
    }
