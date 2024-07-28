package com.ankihighlights.android.repository

import android.util.Log
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
                        response.body()?.let {
                            emit(it)
                        } ?: run {
                            Log.e("HighlightRepo", "Response body is null")
                        }
                    } else {
                        Log.e("HighlightRepo", "Response unsuccessful: ${response.errorBody()}")
                    }
                } catch (e: Exception) {
                    Log.e("HighlightRepo", "Error processing highlights", e)
                }
            }
    }
