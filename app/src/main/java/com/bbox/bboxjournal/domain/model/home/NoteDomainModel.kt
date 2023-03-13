package com.bbox.bboxjournal.domain.model.home

data class NoteDomainModel(
    val id: Int,
    val note: String,
    val dateTime: String,
    val moodColor: MoodColorDomainModel
)