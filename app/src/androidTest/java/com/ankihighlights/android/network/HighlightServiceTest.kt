package com.ankihighlights.android.network

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit

@RunWith(AndroidJUnit4::class)
class HighlightServiceTest {
    private lateinit var apiService: HighlightService

    @Before
    fun setUp() {
        val json = Json { ignoreUnknownKeys = true }
        val retrofit =
            Retrofit.Builder()
                .baseUrl("http://your-flask-api-url/") // Replace with your actual Flask API base URL
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

        apiService = retrofit.create(HighlightService::class.java)
    }

    @Test
    fun testProcessHighlightsApiCall() =
        runBlocking {
            val highlightData = HighlightData("testWord", "testContext", System.currentTimeMillis())
            val response = apiService.processHighlights(highlightData).execute()

            assertEquals("The API call was not successful", true, response.isSuccessful)
            assertEquals("Unexpected response code", 200, response.code())
        }
}
