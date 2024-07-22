package com.ankihighlights.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.network.RetrofitHighlightClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HighlightViewModel @Inject constructor() : ViewModel() {

    fun startListeningForHighlights() {
        viewModelScope.launch {
            while (true) {
                // Placeholder for highlight event listening logic
                delay(5000) // Wait for 5 seconds before checking again
            }
        }
    }

    fun sendHighlightToApi(word: String, context: String, timestamp: Long) {
        val highlightData = HighlightData(word, context, timestamp)
        RetrofitHighlightClient.highlightService.processHighlights(highlightData).enqueue(
            object : Callback<HighlightResponse> {
                override fun onResponse(
                    call: Call<HighlightResponse>,
                    response: Response<HighlightResponse>,
                ) {
                    if (response.isSuccessful) {
                        val highlightResponse = response.body()
                        if (highlightResponse != null && highlightResponse.success) {
                            // Handle success
                        } else {
                            // Handle failure
                        }
                    } else {
                        // Handle failure
                    }
                }

                override fun onFailure(
                    call: Call<HighlightResponse>,
                    t: Throwable,
                ) {
                    // Handle error
                }
            },
        )
    }
}
