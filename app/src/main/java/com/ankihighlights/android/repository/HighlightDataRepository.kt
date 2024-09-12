package com.ankihighlights.android.repository

import android.util.Log
import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.model.cache.HighlightCacheEntity
import com.ankihighlights.android.model.cache.HighlightDAO
import com.ankihighlights.android.repository.service.HighlightService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlightDataRepository
    @Inject
    constructor(
        private val highlightService: HighlightService,
        private val highlightDao: HighlightDAO,
    ) : HighlightRepository {
        override fun processHighlights(highlightData: HighlightData): Flow<HighlightResponse> =
            flow {
                try {
                    val response = highlightService.processHighlights(highlightData) // suspend call
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(it)
                        } ?: Log.e("HighlightRepo", "Response body is null")
                    } else {
                        Log.e("HighlightRepo", "Response unsuccessful: ${response.errorBody()}")
                    }
                } catch (e: Exception) {
                    Log.e("HighlightRepo", "Error processing highlights", e)
                }
            }

        suspend fun cacheHighlight(highlightText: String) {
            highlightDao.insertHighlight(
                HighlightCacheEntity(
                    highlightedText = highlightText,
                    timestamp = System.currentTimeMillis(),
                ),
            )
        }

        fun getCachedHighlights(): Flow<List<HighlightCacheEntity>> {
            return highlightDao.getRecentHighlights()
        }
    }
