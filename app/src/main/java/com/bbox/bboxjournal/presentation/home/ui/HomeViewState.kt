package com.bbox.bboxjournal.presentation.home.ui

import com.bbox.bboxjournal.presentation.home.adapter.ListUiModel
import com.bbox.bboxjournal.presentation.home.ui.HomeViewState.*

/**
 * [HomeViewState]
 * View state for [HomeFragment]
 * consists of [Loading] [Success] [Error]
 */
sealed class HomeViewState {
    object Loading : HomeViewState()
    data class Success(val list: List<ListUiModel>) : HomeViewState()
    object Error : HomeViewState()
}