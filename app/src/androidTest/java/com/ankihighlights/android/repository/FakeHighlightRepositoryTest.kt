package com.ankihighlights.android.repository

import com.ankihighlights.android.model.HighlightData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

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
    fun testProcessHighlightsWithFakeData() =
        runBlocking {
            val highlightData = HighlightData("testWord", "testContext", System.currentTimeMillis())
            val flow = highlightRepository.processHighlights(highlightData)

            // Using flow's first() to get the first emitted value from the flow
            val response = flow.first()

            assertNotNull("Response is null", response)
            assertTrue("Response success field is not true", response.success)
            assertEquals(
                "Unexpected message",
                "Fake highlights processed successfully.",
                response.message,
            )
        }
}
