package com.bbox.bboxjournal.presentation.detail.ui

import com.bbox.bboxjournal.presentation.model.JournalUiModel

/**
 * [DetailViewState]
 * View state to handle all view states of [DetailFragment]
 */
sealed class DetailViewState {
    /**
     * [LoadDetailViewState]
     * A view state to handle of loading the detail of journal
     * consists of [LoadDetailViewState.Loading] [LoadDetailViewState.Success] [LoadDetailViewState.Error]
     */
    sealed class LoadDetailViewState : DetailViewState() {
        object Loading : LoadDetailViewState()
        data class Success(val journalUiModel: JournalUiModel) : LoadDetailViewState()
        object Error : LoadDetailViewState()
    }
    /**
     * [DeleteJournalViewState]
     * A view state to handle when the journal get deleted
     * consists of [DeleteJournalViewState.Loading] [DeleteJournalViewState.Success] [DeleteJournalViewState.Error]
     */

    sealed class DeleteJournalViewState : DetailViewState() {
        object Loading : DeleteJournalViewState()
        object Success : DeleteJournalViewState()
        object Error : DeleteJournalViewState()
    }

}
