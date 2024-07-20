package com.ankihighlights.android.network

import android.content.Context
import com.ankihighlights.android.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call.Factory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitHighlightController
    @Inject
    constructor(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Factory>,
        val context: Context,
    ) {
        private val networkApi =
            Retrofit.Builder()
                // .baseUrl(BuildConfig.BASE_URL)
                .baseUrl(BuildConfig.BASE_URL)
                .callFactory { okhttpCallFactory.get().newCall(it) }
                .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(HighlightService::class.java)

        fun processHighlights(highlightData: HighlightData) = networkApi.processHighlights(highlightData)
    }
