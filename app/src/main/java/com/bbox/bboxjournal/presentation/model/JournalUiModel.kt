package com.bbox.bboxjournal.presentation.model

import com.bbox.bboxjournal.domain.model.JournalDomainModel

/**
 * [JournalUiModel]
 * A model class for presentation layer
 */
data class JournalUiModel(
    val id: Int = 0,
    val moodColor: MoodColorUiModel,
    val note: String,
    var dateTime: String = ""
)

/**
 * [JournalUiModel.toJournalDomainModel]
 * An extension function to convert [JournalUiModel] into [JournalDomainModel]
 * @return [JournalDomainModel]
 */
fun JournalUiModel.toJournalDomainModel(): JournalDomainModel {
    return JournalDomainModel(
        id = id,
        note = note,
        moodColor = moodColor.name,
        dateTime = dateTime
    )
}

/**
 * [JournalDomainModel.toJournalUiModel]
 * An extension function to convert [JournalDomainModel] into [JournalUiModel]
 * this function also convert moodColor into [MoodColorUiModel]
 * @return [JournalUiModel]
 */
fun JournalDomainModel.toJournalUiModel(): JournalUiModel {
    return JournalUiModel(
        id = id,
        note = note,
        moodColor = if (moodColor == MoodColorUiModel.GREEN.name) MoodColorUiModel.GREEN else if (moodColor == MoodColorUiModel.RED.name) MoodColorUiModel.RED else if (moodColor == MoodColorUiModel.YELLOW.name) MoodColorUiModel.YELLOW else MoodColorUiModel.GREEN,
        dateTime = dateTime
    )
}