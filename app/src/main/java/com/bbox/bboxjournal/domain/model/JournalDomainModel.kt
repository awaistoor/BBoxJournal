package com.bbox.bboxjournal.domain.model

data class JournalDomainModel(
    val id: Int = 0,
    val note: String,
    val dateTime: String,
    val moodColor: String
)