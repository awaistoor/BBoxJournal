package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AddNoteUseCaseTest {

    private lateinit var SUT: AddNoteUseCase

    @Mock
    private lateinit var repository: JournalRepository

    @Before
    fun setup() {
        SUT = AddNoteUseCase(repository = repository)
    }

    @Test
    fun testInvoke() = runBlocking {
        Mockito.`when`(repository.addJournal(getDummyJournal())).thenReturn(Unit)
        SUT.invoke(getDummyJournal())
        Mockito.verify(repository).addJournal(getDummyJournal())
    }

    private fun getDummyJournal(): JournalDomainModel {
        return JournalDomainModel(
            id = 0,
            note = "",
            moodColor = "",
            dateTime = ""
        )
    }
}