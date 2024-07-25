package com.ankihighlights.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.repository.service.HighlightService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HighlightDataRepository @Inject constructor(
    private val highlightService: HighlightService
) : HighlightRepository {

    override fun processHighlights(highlightData: HighlightData): LiveData<HighlightResponse> {
        val data = MutableLiveData<HighlightResponse>()
        highlightService.processHighlights(highlightData)
            .enqueue(object : Callback<HighlightResponse> {
                override fun onResponse(
                    call: Call<HighlightResponse>,
                    response: Response<HighlightResponse>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }

                }

                override fun onFailure(call: Call<HighlightResponse>, t: Throwable) {
                    // TODO: Handle network errors
                }
            })
        return data
    }
}
