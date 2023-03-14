package com.bbox.bboxjournal.presentation.model

import com.bbox.bboxjournal.domain.model.JournalDomainModel

data class AddNoteUiModel(
    val moodColor: MoodColorUiModel,
    val note: String
)

fun AddNoteUiModel.toJournalDomainModel(dateTime: String) : JournalDomainModel{
    return JournalDomainModel(
        note = note,
        moodColor = moodColor.name,
        dateTime = dateTime
    )
}