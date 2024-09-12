package com.ankihighlights.android.repository

import com.ankihighlights.android.model.cache.HighlightCacheEntity
import com.ankihighlights.android.model.cache.HighlightDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Fake implementation of the [HighlightDAO] that allows tests to run without a real database.
 */
class FakeHighlightDAO
    @Inject
    constructor() : HighlightDAO {
        // Simulates the insertion of a highlight into the database
        override suspend fun insertHighlight(highlight: HighlightCacheEntity) {
            // No-op (not testing actual Room behavior here)
        }

        // Simulates fetching recent highlights from the database
        override fun getRecentHighlights(): Flow<List<HighlightCacheEntity>> {
            // Return a fake flow of highlights
            return flow {
                val fakeData =
                    listOf(
                        HighlightCacheEntity(
                            id = 1,
                            highlightedText = "Fake Highlight",
                            timestamp = 1234567890L,
                        ),
                    )
                emit(fakeData)
            }
        }
    }
