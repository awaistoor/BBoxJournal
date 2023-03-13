package com.bbox.bboxjournal.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbox.bboxjournal.domain.model.home.NoteDomainModel

@Entity(tableName = "note")
data class NoteEntityModel(
    @PrimaryKey val id: Int,
    val note: String,
    val dateTime: String,
    @Embedded val moodColor: MoodColorEntityModel
)

fun NoteEntityModel.toDomainModel(): NoteDomainModel {
    return NoteDomainModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor.toDomainModel()
    )
}