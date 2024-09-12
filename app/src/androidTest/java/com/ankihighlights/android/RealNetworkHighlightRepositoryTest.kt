package com.ankihighlights.android

import com.ankihighlights.android.data.network.HighlightApi
import com.ankihighlights.android.data.repository.NetworkHighlightDataRepository
import com.ankihighlights.android.data.repository.NetworkHighlightRepository
import com.ankihighlights.android.model.HighlightData
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit

@RunWith(JUnit4::class)
class RealNetworkHighlightRepositoryTest {
    private lateinit var networkHighlightRepository: NetworkHighlightRepository

    @Before
    fun setUp() {
        val json = Json { ignoreUnknownKeys = true }
        val client = OkHttpClient.Builder().build()

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()

        val highlightApi = retrofit.create(HighlightApi::class.java)

        networkHighlightRepository = NetworkHighlightDataRepository(highlightApi)
    }

    @Test
    fun testProcessHighlightsWithRealApi() =
        runBlocking {
            val highlightData = HighlightData("testWord", "testContext", System.currentTimeMillis())
            val highlightFlow = networkHighlightRepository.processHighlights(highlightData)

            val response = highlightFlow.first()

            assertNotNull("Response is null", response)
            assertTrue("Response success field is not true", response.success)
            assertNotNull("Response data field is null", response.data)
            assertEquals("Unexpected message", "highlights processed successfully.", response.message)
        }
}
