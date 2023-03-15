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
    private val addNoteViewState = MutableLiveData<AddNoteViewState>()
    val addNoteUiState: LiveData<AddNoteViewState> = addNoteViewState

    fun saveJournal(journalUiModel: JournalUiModel) {
        viewModelScope.launch {
            addNoteViewState.postValue(AddNoteViewState.Loading)
            try {
                val dateTime = getCurrentDateTimeUseCase.invoke()
                addNoteUseCase.invoke(journalUiModel.apply { this.dateTime = dateTime }
                    .toJournalDomainModel())
                addNoteViewState.postValue(AddNoteViewState.Success)
            } catch (ex: Exception) {
                ex.printStackTrace()
                addNoteViewState.postValue(AddNoteViewState.Error)
            }
        }

    }

}