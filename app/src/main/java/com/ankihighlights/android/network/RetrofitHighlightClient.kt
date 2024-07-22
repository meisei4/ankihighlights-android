package com.ankihighlights.android.network

import com.ankihighlights.android.network.HighlightService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.Retrofit
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

object RetrofitClient {
    private const val BASE_URL = "http://your-api-url.com/"

    private val contentType = "application/json".toMediaType()

    private val json = Json { ignoreUnknownKeys = true }

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    val highlightService: HighlightService by lazy {
        instance.create(HighlightService::class.java)
    }
}
