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
class GetJournalByIdUseCaseTest {

    private lateinit var SUT: GetJournalByIdUseCase

    @Mock
    private lateinit var repository: JournalRepository

    @Before
    fun setup() {
        SUT = GetJournalByIdUseCase(repository = repository)
    }

    @Test
    fun testInvoke() = runBlocking {
        Mockito.`when`(repository.getJournalById(getDummyJournalId())).thenReturn(getDummyJournal())
        Assert.assertEquals(
            repository.getJournalById(getDummyJournalId()),
            SUT.invoke(getDummyJournalId())
        )
    }

    private fun getDummyJournal(): JournalDomainModel {
        return JournalDomainModel(
            id = 0,
            note = "DummyNote",
            dateTime = "DummyDateTime",
            moodColor = "DummyMoodColor"
        )
    }

    private fun getDummyJournalId(): Int = 0

}