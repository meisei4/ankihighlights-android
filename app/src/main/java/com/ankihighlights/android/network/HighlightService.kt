package com.ankihighlights.android.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HighlightService {
    @POST("highlight/process")
    fun processHighlights(
        @Body highlightData: HighlightData,
    ): Call<Void>
}

data class HighlightData(
    val word: String,
    val context: String,
    val timestamp: Long,
)
