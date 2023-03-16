package com.bbox.bboxjournal.presentation.addnote.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.usecase.AddNoteUseCase
import com.bbox.bboxjournal.domain.usecase.GetCurrentDateTimeUseCase
import com.bbox.bboxjournal.presentation.addnote.ui.AddNoteViewState
import com.bbox.bboxjournal.presentation.model.toJournalUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class AddNoteViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var SUT: AddNoteViewModel

    @Mock
    private lateinit var addNoteUseCase: AddNoteUseCase

    @Mock
    private lateinit var getCurrentDateTimeUseCase: GetCurrentDateTimeUseCase

    @Mock
    private lateinit var addNoteViewStateObserver: Observer<AddNoteViewState>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
        SUT = AddNoteViewModel(
            addNoteUseCase = addNoteUseCase,
            getCurrentDateTimeUseCase = getCurrentDateTimeUseCase
        )
    }

    @Test
    fun `when saveJournal is called, invoke getCurrentDateTimeUseCase, invoke addNoteUseCase, return success`() =
        runBlocking {
            Mockito.`when`(getCurrentDateTimeUseCase.invoke()).thenReturn(getDummyCurrentDate())
            Mockito.`when`(addNoteUseCase.invoke(getDummyJournalModel())).thenReturn(Unit)
            SUT.saveJournal(getDummyJournalModel().toJournalUiModel())
            SUT.addNoteUiState.observeForever(addNoteViewStateObserver)
            Mockito.verify(getCurrentDateTimeUseCase).invoke()
            Mockito.verify(addNoteUseCase).invoke(getDummyJournalModel())
            Mockito.verify(addNoteViewStateObserver).onChanged(AddNoteViewState.Success)
            SUT.addNoteUiState.removeObserver(addNoteViewStateObserver)
        }

    @Test
    fun `when saveJournal is called, invoke getCurrentDateTimeUseCase, invoke addNoteUseCase,throw exception, return error`() =
        runBlocking {
            Mockito.`when`(getCurrentDateTimeUseCase.invoke()).thenReturn(getDummyCurrentDate())
            Mockito.`when`(addNoteUseCase.invoke(getDummyJournalModel())).thenThrow(RuntimeException())
            SUT.saveJournal(getDummyJournalModel().toJournalUiModel())
            SUT.addNoteUiState.observeForever(addNoteViewStateObserver)
            Mockito.verify(getCurrentDateTimeUseCase).invoke()
            Mockito.verify(addNoteUseCase).invoke(getDummyJournalModel())
            Mockito.verify(addNoteViewStateObserver).onChanged(AddNoteViewState.Error)
            SUT.addNoteUiState.removeObserver(addNoteViewStateObserver)
        }

    private fun getDummyJournalModel(): JournalDomainModel {
        return JournalDomainModel(
            id = 0,
            note = "DummyNote",
            dateTime = getDummyCurrentDate(),
            moodColor = "GREEN",
        )
    }

    private fun getDummyCurrentDate(): String = "2023-03-17T01:23:03.569+0500"


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

}