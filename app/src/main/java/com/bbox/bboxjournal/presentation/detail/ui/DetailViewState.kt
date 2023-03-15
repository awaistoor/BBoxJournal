package com.bbox.bboxjournal.presentation.detail.ui

import com.bbox.bboxjournal.presentation.model.JournalUiModel

sealed class DetailViewState {
    sealed class LoadDetailViewState : DetailViewState() {
        object Loading : LoadDetailViewState()
        data class Success(val journalUiModel: JournalUiModel) : LoadDetailViewState()
        object Error : LoadDetailViewState()
    }

    sealed class DeleteJournalViewState : DetailViewState() {
        object Loading : DeleteJournalViewState()
        object Success : DeleteJournalViewState()
        object Error : DeleteJournalViewState()
    }

}
