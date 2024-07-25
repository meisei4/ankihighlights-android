package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

//TODO: this doesnt just work without defining a target app that can be found in a corresponding manifest
//  This is the BIGGEST t2do for next time i look at this code.
//  -- Need to focus on why Nia has its directories split up so much like it does,
//  -- rather than just sticking to the simple Main, Integration test, and Unit test it has many test directories outside of that
@HiltAndroidTest
class FakeHighlightRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var highlightRepository: HighlightRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testProcessHighlightsWithFakeData() {
        runBlocking {
            val highlightData = HighlightData("testWord", "testContext", System.currentTimeMillis())
            val liveData = highlightRepository.processHighlights(highlightData)

            liveData.observeForever { response ->
                assertNotNull("Response is null", response)
                assertTrue("Response success field is not true", response?.success ?: false)
                assertEquals(
                    "Unexpected message",
                    "Fake highlights processed successfully.",
                    response?.message
                )
            }
        }
    }
}
