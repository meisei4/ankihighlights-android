package com.ankihighlights.android.network

import com.ankihighlights.android.BuildConfig
import com.ankihighlights.android.repository.service.HighlightService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitHighlightClient @Inject constructor(
    json: Json,
    okHttpClient: OkHttpClient
) {

    private val contentType = "application/json".toMediaType()

    val highlightService: HighlightService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(HighlightService::class.java)
}
