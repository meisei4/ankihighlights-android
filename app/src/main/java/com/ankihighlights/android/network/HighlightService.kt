package com.ankihighlights.android.network

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface HighlightService {
    @POST("highlight/process")
    fun processHighlights(
        @Body highlightData: HighlightData,
    ): Call<HighlightResponse>
}
