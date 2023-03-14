package com.bbox.bboxjournal.presentation.addnote.ui

sealed class AddNoteViewState {
    object Loading : AddNoteViewState()
    object Success : AddNoteViewState()
    object Error : AddNoteViewState()
}