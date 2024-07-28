package com.ankihighlights.android.repository.service

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.model.TestResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HighlightService {
    @POST("highlight/process")
    suspend fun processHighlights(
        @Body highlightData: HighlightData,
    ): Response<HighlightResponse>

    @GET("/highlight/test")
    suspend fun testEndpoint(): Response<TestResponse>
}
