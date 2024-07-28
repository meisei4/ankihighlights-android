package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import com.ankihighlights.android.model.HighlightDataWrapper
import com.ankihighlights.android.model.HighlightResponse
import com.ankihighlights.android.model.dto.HighlightDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Fake implementation of the [HighlightRepository] that retrieves the highlights from a hardcoded [HighlightDTO].
 *
 * This allows us to run the app with fake data, without needing an internet connection or working
 * backend.
 *
 *
 * This literally taken from Nia's use of Hilt. i.e. Mockito NEVER AGAIN
 */

class FakeHighlightRepository
    @Inject
    constructor() : HighlightRepository {
        override fun processHighlights(highlightData: HighlightData): Flow<HighlightResponse> =
            flow {
                val fakeData =
                    listOf(
                        HighlightDTO(
                            id = 1,
                            word = "test",
                            context = "example",
                            timestamp = 1234567890L,
                        ),
                    )
                val fakeResponse =
                    HighlightResponse(
                        success = true,
                        data = HighlightDataWrapper(fakeData),
                        message = "Fake highlights processed successfully.",
                    )

                emit(fakeResponse)
            }
    }
