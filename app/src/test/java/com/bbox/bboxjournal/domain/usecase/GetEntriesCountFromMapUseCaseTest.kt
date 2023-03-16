package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetEntriesCountFromMapUseCaseTest {

    private lateinit var SUT: GetEntriesCountFromMapUseCase

    @Before
    fun setup() {
        SUT = GetEntriesCountFromMapUseCase()
    }

    @Test
    fun `invoke should return the total count in all list in the provided map`() {
        val result = SUT.invoke(getDummyMap())
        Assert.assertEquals(3, result)
    }

    private fun getDummyMap(): Map<String, List<JournalDomainModel>> {
        return mapOf(
            "Tue, 15" to listOf(
                JournalDomainModel(
                    id = 0,
                    note = "DummyNote",
                    dateTime = "DummyTime",
                    moodColor = "DummyColor"
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
                )
            )
        )
    }
}