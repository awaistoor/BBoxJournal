package com.bbox.bboxjournal.presentation.home.ui

import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel

sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val list: List<ListUiModel>) : HomeViewState()
    object Error : HomeViewState()
}