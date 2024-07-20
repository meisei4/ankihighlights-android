package com.example.anki_highlights.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("vocab_highlights/process")
    fun processHighlights(@Body highlightData: HighlightData): Call<Void>
}

data class HighlightData(
    val word: String,
    val context: String,
    val timestamp: Long
)
