package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
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
    ) : HighlightRepository {
        override fun processHighlights(highlightData: HighlightData): Flow<HighlightResponse> =
            flow {
                try {
                    val response = highlightService.processHighlights(highlightData).execute()
                    if (response.isSuccessful) {
                        response.body()?.let { emit(it) }
                    } else {
                        // Handle response errors, e.g., throw an exception or emit an error state
                    }
                } catch (e: Exception) {
                    // Handle network or other errors
                    // Optionally emit an error state
                }
            }
    }
