package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.repository.JournalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DeleteJournalUseCaseTest {
    private lateinit var SUT: DeleteJournalUseCase

    @Mock
    private lateinit var repository: JournalRepository

    @Before
    fun setup() {
        SUT = DeleteJournalUseCase(repository = repository)
    }

    @Test
    fun testInvoke() = runBlocking {
        Mockito.`when`(repository.deleteJournal(getDummyJournalId())).thenReturn(Unit)
        SUT.invoke(getDummyJournalId())
        Mockito.verify(repository).deleteJournal(getDummyJournalId())
    }

    private fun getDummyJournalId(): Int = 0
}