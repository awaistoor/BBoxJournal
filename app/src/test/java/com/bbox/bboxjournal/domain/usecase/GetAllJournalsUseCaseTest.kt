package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllJournalsUseCaseTest {

    private lateinit var SUT: GetAllJournalsUseCase

    @Mock
    private lateinit var journalRepository: JournalRepository

    @Mock
    private lateinit var convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase

    @Before
    fun setup() {
        SUT = GetAllJournalsUseCase(
            journalRepository = journalRepository,
            convertDateTimeFormatUseCase = convertDateTimeFormatUseCase
        )
    }

    @Test
    fun `invoke should return a HashMap grouped by month and day of the week`() = runBlocking {
        Mockito.`when`(
            convertDateTimeFormatUseCase.invoke(
                "2023-03-15T03:12:29.877+0500",
                "MMMM, yyyy"
            )
        ).thenReturn("March, 2023")
        Mockito.`when`(
            convertDateTimeFormatUseCase.invoke(
                "2023-03-16T03:12:29.877+0500",
                "MMMM, yyyy"
            )
        ).thenReturn("March, 2023").thenReturn("March, 2023")

        Mockito.`when`(
            convertDateTimeFormatUseCase.invoke(
                "2023-03-15T03:12:29.877+0500",
                "EEE, dd"
            )
        ).thenReturn("Tue, 15")
        Mockito.`when`(
            convertDateTimeFormatUseCase.invoke(
                "2023-03-16T03:12:29.877+0500",
                "EEE, dd"
            )
        ).thenReturn("Wed, 16").thenReturn("Wed, 16")

        Mockito.`when`(journalRepository.getAllJournals()).thenReturn(getDummyJournalList())
        val result = SUT.invoke()
        Assert.assertEquals(getDummyHashMap(), result)
    }

    private fun getDummyHashMap(): HashMap<String, Map<String, List<JournalDomainModel>>> {
        return hashMapOf(
            "March, 2023" to hashMapOf(
                "Tue, 15" to listOf(
                    JournalDomainModel(
                        id = 0,
                        note = "Dummy Journal 1",
                        moodColor = "DummyColor",
                        dateTime = "2023-03-15T03:12:29.877+0500"
                    )
                ),
                "Wed, 16" to listOf(
                    JournalDomainModel(
                        id = 1,
                        note = "Dummy Journal 2",
                        moodColor = "DummyColor",
                        dateTime = "2023-03-16T03:12:29.877+0500"
                    ),
                    JournalDomainModel(
                        id = 2,
                        note = "Dummy Journal 3",
                        moodColor = "DummyColor",
                        dateTime = "2023-03-16T03:12:29.877+0500"
                    )
                )
            )
        )
    }

    private fun getDummyJournalList(): List<JournalDomainModel> {
        return listOf(
            JournalDomainModel(
                id = 0,
                note = "Dummy Journal 1",
                moodColor = "DummyColor",
                dateTime = "2023-03-15T03:12:29.877+0500"
            ),
            JournalDomainModel(
                id = 1,
                note = "Dummy Journal 2",
                moodColor = "DummyColor",
                dateTime = "2023-03-16T03:12:29.877+0500"
            ),
            JournalDomainModel(
                id = 2,
                note = "Dummy Journal 3",
                moodColor = "DummyColor",
                dateTime = "2023-03-16T03:12:29.877+0500"
            )
        )
    }
}