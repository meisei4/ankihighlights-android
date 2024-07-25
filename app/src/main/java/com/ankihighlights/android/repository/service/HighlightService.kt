package com.ankihighlights.android.repository.service

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.model.TestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HighlightService {

    @POST("highlight/process")
    fun processHighlights(
        @Body highlightData: HighlightData
    ): Call<HighlightResponse>


    @GET("/highlight/test")
    fun testEndpoint(): Call<TestResponse>

}




