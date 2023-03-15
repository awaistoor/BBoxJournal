package com.bbox.bboxjournal.presentation.home.adapter

import com.bbox.bboxjournal.presentation.model.JournalUiModel

data class ListUiModel(
    val itemType: ItemType,
    val headerText: String? = null,
    val listItem: JournalUiModel? = null
) {
    enum class ItemType {
        MONTH_HEADER_ITEM, DAY_HEADER_ITEM, LIST_ITEM
    }
}


