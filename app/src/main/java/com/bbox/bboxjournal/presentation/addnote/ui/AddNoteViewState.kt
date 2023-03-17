package com.bbox.bboxjournal.presentation.addnote.ui

/**
 * [AddNoteViewState]
 * View state for adding Journal for [AddNoteFragment]
 * consists of [Loading] [Success] [Error]
 */
sealed class AddNoteViewState {
    object Loading : AddNoteViewState()
    object Success : AddNoteViewState()
    object Error : AddNoteViewState()
}