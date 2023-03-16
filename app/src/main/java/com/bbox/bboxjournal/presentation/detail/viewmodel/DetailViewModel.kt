package com.bbox.bboxjournal.presentation.detail.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.bbox.bboxjournal.domain.usecase.DeleteJournalUseCase
import com.bbox.bboxjournal.domain.usecase.GetJournalByIdUseCase
import com.bbox.bboxjournal.presentation.detail.ui.DetailFragmentArgs
import com.bbox.bboxjournal.presentation.detail.ui.DetailViewState
import com.bbox.bboxjournal.presentation.model.toJournalUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getJournalByIdUseCase: GetJournalByIdUseCase,
    private val deleteJournalUseCase: DeleteJournalUseCase
) :
    ViewModel() {
    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val loadDetailViewState = MutableLiveData<DetailViewState.LoadDetailViewState>()
    private val deleteJournalViewState = MutableLiveData<DetailViewState.DeleteJournalViewState>()
    val loadDetailUiState: LiveData<DetailViewState.LoadDetailViewState> = loadDetailViewState
    val deleteJournalUiState: LiveData<DetailViewState.DeleteJournalViewState> =
        deleteJournalViewState

    init {
        loadDate(args.journalId)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadDate(journalId: Int) {
        viewModelScope.launch {
            loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Loading)
            try {
                val journal = getJournalByIdUseCase.invoke(journalId)
                loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Success(journal.toJournalUiModel()))
            } catch (ex: Exception) {
                ex.printStackTrace()
                loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Error)
            }
        }
    }

    fun deleteJournal() {
        viewModelScope.launch {
            deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Loading)
            try {
                deleteJournalUseCase.invoke(args.journalId)
                deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Success)
            } catch (ex: Exception) {
                ex.printStackTrace()
                deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Error)
            }
        }
    }
}