package com.bbox.bboxjournal.presentation.model

import com.bbox.bboxjournal.domain.model.JournalDomainModel

data class JournalUiModel(
    val moodColor: MoodColorUiModel,
    val note: String,
    var dateTime: String = ""
)

fun JournalUiModel.toJournalDomainModel(): JournalDomainModel {
    return JournalDomainModel(
        note = note,
        moodColor = moodColor.name,
        dateTime = dateTime
    )
}

fun JournalDomainModel.toJournalUiModel(): JournalUiModel {
    return JournalUiModel(
        note = note,
        moodColor = if (moodColor == MoodColorUiModel.GREEN.name) MoodColorUiModel.GREEN else if (moodColor == MoodColorUiModel.RED.name) MoodColorUiModel.RED else if (moodColor == MoodColorUiModel.YELLOW.name) MoodColorUiModel.YELLOW else MoodColorUiModel.GREEN,
        dateTime = dateTime
    )
}