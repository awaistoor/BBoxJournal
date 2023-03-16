package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCumulativeMoodColorUseCaseTest {

    private lateinit var SUT: GetCumulativeMoodColorUseCase

    @Before
    fun setup() {
        SUT = GetCumulativeMoodColorUseCase()
    }

    @Test
    fun `invoke should return the most frequent MoodColor in the provided Map`() {
        val result = SUT.invoke(getDummyMap())
        Assert.assertEquals("YELLOW", result)
    }

    private fun getDummyMap(): Map<String, List<JournalDomainModel>> {
        return mapOf(
            "Tue, 15" to listOf(
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "YELLOW"
                ),
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "YELLOW"
                ),
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "RED"
                )

            ),
            "Wed, 16" to listOf(
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "YELLOW"
                ),
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "GREEN"
                ),
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "RED"
                )
            )
        )
    }
}