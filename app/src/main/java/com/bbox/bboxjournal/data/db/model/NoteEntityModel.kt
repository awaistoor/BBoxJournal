package com.bbox.bboxjournal.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntityModel(
    @PrimaryKey val id: Int,
    val note: String,
    val dateTime: String,
    @Embedded val moodColor: MoodColorEntityModel
)