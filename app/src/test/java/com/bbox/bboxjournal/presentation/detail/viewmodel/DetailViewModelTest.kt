package com.bbox.bboxjournal.presentation.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.usecase.DeleteJournalUseCase
import com.bbox.bboxjournal.domain.usecase.GetJournalByIdUseCase
import com.bbox.bboxjournal.presentation.detail.ui.DetailViewState
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
class DetailViewModelTest {
    private lateinit var SUT: DetailViewModel

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    @Mock
    private lateinit var getJournalByIdUseCase: GetJournalByIdUseCase

    @Mock
    private lateinit var deleteJournalUseCase: DeleteJournalUseCase

    @Mock
    private lateinit var loadDetailViewState: Observer<DetailViewState.LoadDetailViewState>

    @Mock
    private lateinit var deleteJournalViewState: Observer<DetailViewState.DeleteJournalViewState>

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun `on viewModel load, invoke getJournalByIdUseCase, return success with data`() =
        runBlocking {
            Mockito.`when`(getJournalByIdUseCase(getDummyJournalId())).thenReturn(getDummyJournal())
            SUT = DetailViewModel(savedStateHandle, getJournalByIdUseCase, deleteJournalUseCase)
            SUT.loadDetailUiState.observeForever(loadDetailViewState)
            Mockito.verify(getJournalByIdUseCase).invoke(getDummyJournalId())
            Mockito.verify(loadDetailViewState)
                .onChanged(DetailViewState.LoadDetailViewState.Success(getDummyJournal().toJournalUiModel()))
            SUT.loadDetailUiState.removeObserver(loadDetailViewState)
        }

    @Test
    fun `on viewModel load, invoke getJournalByIdUseCase, throw exception, return error`() =
        runBlocking {
            Mockito.`when`(getJournalByIdUseCase(getDummyJournalId())).thenThrow(RuntimeException())
            SUT = DetailViewModel(savedStateHandle, getJournalByIdUseCase, deleteJournalUseCase)
            SUT.loadDetailUiState.observeForever(loadDetailViewState)
            Mockito.verify(getJournalByIdUseCase).invoke(getDummyJournalId())
            Mockito.verify(loadDetailViewState)
                .onChanged(DetailViewState.LoadDetailViewState.Error)
            SUT.loadDetailUiState.removeObserver(loadDetailViewState)
        }

    @Test
    fun `when deleteJournal called, invoke deleteJournalUseCase, return success`() = runBlocking {
        Mockito.`when`(deleteJournalUseCase.invoke(getDummyJournalId())).thenReturn(Unit)
        SUT = DetailViewModel(savedStateHandle, getJournalByIdUseCase, deleteJournalUseCase)
        SUT.deleteJournalUiState.observeForever(deleteJournalViewState)
        SUT.deleteJournal()
        Mockito.verify(deleteJournalUseCase).invoke(getDummyJournalId())
        Mockito.verify(deleteJournalViewState)
            .onChanged(DetailViewState.DeleteJournalViewState.Success)
        SUT.deleteJournalUiState.removeObserver(deleteJournalViewState)
    }

    @Test
    fun `when deleteJournal called, invoke deleteJournalUseCase, throw exception, return error`() =
        runBlocking {
            Mockito.`when`(deleteJournalUseCase.invoke(getDummyJournalId()))
                .thenThrow(RuntimeException())
            SUT = DetailViewModel(savedStateHandle, getJournalByIdUseCase, deleteJournalUseCase)
            SUT.deleteJournalUiState.observeForever(deleteJournalViewState)
            SUT.deleteJournal()
            Mockito.verify(deleteJournalUseCase).invoke(getDummyJournalId())
            Mockito.verify(deleteJournalViewState)
                .onChanged(DetailViewState.DeleteJournalViewState.Error)
            SUT.deleteJournalUiState.removeObserver(deleteJournalViewState)
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


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}