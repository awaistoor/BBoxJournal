package com.bbox.bboxjournal.presentation.home.adapter

import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.presentation.model.MoodColorUiModel

data class ListUiModel(
    val itemType: ItemType,
    val header: HeaderUiModel? = null,
    val listItem: JournalUiModel? = null
) {
    enum class ItemType {
        MONTH_HEADER_ITEM, DAY_HEADER_ITEM, LIST_ITEM
    }

    data class HeaderUiModel(
        val headerTitle: String,
        val headerColor: MoodColorUiModel,
        val entriesCount: Int
    )
}


