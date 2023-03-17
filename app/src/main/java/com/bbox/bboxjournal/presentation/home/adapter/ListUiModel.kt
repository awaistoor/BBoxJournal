package com.bbox.bboxjournal.presentation.home.adapter

import com.bbox.bboxjournal.presentation.model.JournalUiModel
import com.bbox.bboxjournal.presentation.model.MoodColorUiModel

/**
 * [ListUiModel]
 * A UI model class for [JournalListAdapter]
 */
data class ListUiModel(
    val itemType: ItemType,
    val header: HeaderUiModel? = null,
    val listItem: JournalUiModel? = null
) {
    /**
     * [ItemType]
     * Type of the item in the list adapter
     */
    enum class ItemType {
        MONTH_HEADER_ITEM, DAY_HEADER_ITEM, LIST_ITEM
    }

    /**
     * [HeaderUiModel]
     * A UI model class for the different type of header in the list
     */
    data class HeaderUiModel(
        val headerTitle: String,
        val headerColor: MoodColorUiModel,
        val entriesCount: Int
    )
}


