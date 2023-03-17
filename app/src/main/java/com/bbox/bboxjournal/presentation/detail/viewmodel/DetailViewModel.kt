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
    // args to get arguments from DetailFragment
    private val args = DetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    // a mutable live data to observe the loading UI state
    private val loadDetailViewState = MutableLiveData<DetailViewState.LoadDetailViewState>()
    // a mutable live data to observe the UI state when journal get deleted
    private val deleteJournalViewState = MutableLiveData<DetailViewState.DeleteJournalViewState>()
    // a live data which will expose loading view state to the view
    val loadDetailUiState: LiveData<DetailViewState.LoadDetailViewState> = loadDetailViewState
    // a live data which will expose view state to the view when journal get deleted
    val deleteJournalUiState: LiveData<DetailViewState.DeleteJournalViewState> =
        deleteJournalViewState

    init {
        // request data on start of this view model
        loadDate(args.journalId)
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun loadDate(journalId: Int) {
        viewModelScope.launch {
            // emit UI state to loading
            loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Loading)
            try {
                // request for journal with specific ID
                val journal = getJournalByIdUseCase.invoke(journalId)
                // emit UI state to Success with journal data model
                loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Success(journal.toJournalUiModel()))
            } catch (ex: Exception) {
                ex.printStackTrace()
                // emit UI state to Error
                loadDetailViewState.postValue(DetailViewState.LoadDetailViewState.Error)
            }
        }
    }

    fun deleteJournal() {
        viewModelScope.launch {
            // emit UI state to loading
            deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Loading)
            try {
                // request to delete a journal by providing specific ID
                deleteJournalUseCase.invoke(args.journalId)
                // emit UI state to Success
                deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Success)
            } catch (ex: Exception) {
                ex.printStackTrace()
                // emit UI state to Error
                deleteJournalViewState.postValue(DetailViewState.DeleteJournalViewState.Error)
            }
        }
    }
}