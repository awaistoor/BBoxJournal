package com.bbox.bboxjournal.domain.model

data class JournalDomainModel(
    val id: Int,
    val note: String,
    val dateTime: String,
    val moodColor: MoodColorDomainModel
)