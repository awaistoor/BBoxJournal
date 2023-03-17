package com.bbox.bboxjournal.presentation.addnote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bbox.bboxjournal.domain.usecase.AddNoteUseCase
import com.bbox.bboxjournal.domain.usecase.GetCurrentDateTimeUseCase
import com.bbox.bboxjournal.presentation.addnote.ui.AddNoteViewState
import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.presentation.model.toJournalDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getCurrentDateTimeUseCase: GetCurrentDateTimeUseCase
) : ViewModel() {
    // a mutable live data to observe the UI state
    private val addNoteViewState = MutableLiveData<AddNoteViewState>()

    // a live data which will expose view state to the view
    val addNoteUiState: LiveData<AddNoteViewState> = addNoteViewState

    fun saveJournal(journalUiModel: JournalUiModel) {
        viewModelScope.launch {
            // emit UI state to loading
            addNoteViewState.postValue(AddNoteViewState.Loading)
            try {
                // get latest date
                val dateTime = getCurrentDateTimeUseCase.invoke()
                // invoke the addNoteUseCase
                // set the date to current dateTime
                addNoteUseCase.invoke(journalUiModel.apply { this.dateTime = dateTime }
                    .toJournalDomainModel())
                // emit UI state to Success
                addNoteViewState.postValue(AddNoteViewState.Success)
            } catch (ex: Exception) {
                ex.printStackTrace()
                // emit UI state to error
                addNoteViewState.postValue(AddNoteViewState.Error)
            }
        }

    }

}