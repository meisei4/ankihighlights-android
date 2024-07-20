package com.ankihighlights.android.network

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Call.Factory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitHighlightController {
    @POST("highlight/process")
    fun processHighlights(
        @Body highlightData: HighlightData,
    ): Call<ResponseBody>
}

private const val ANKI_BASE_URL = "https://localhost/" // Replace with your actual base URL

@Singleton
class RetrofitAnkiNetwork
    @Inject
    constructor(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Factory>,
        val context: Context,
    ) {
        private val networkApi =
            Retrofit.Builder()
                .baseUrl(ANKI_BASE_URL)
                .callFactory { okhttpCallFactory.get().newCall(it) }
                .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(RetrofitHighlightController::class.java)

        fun processHighlights(highlightData: HighlightData): Call<ResponseBody> = networkApi.processHighlights(highlightData)
    }
