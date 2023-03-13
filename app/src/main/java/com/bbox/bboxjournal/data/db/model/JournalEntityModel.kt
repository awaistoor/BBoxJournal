package com.bbox.bboxjournal.data.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbox.bboxjournal.domain.model.JournalDomainModel

@Entity(tableName = "journal")
data class JournalEntityModel(
    @PrimaryKey val id: Int,
    val note: String,
    val dateTime: String,
    @Embedded val moodColor: MoodColorEntityModel
)

fun JournalEntityModel.toDomainModel(): JournalDomainModel {
    return JournalDomainModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor.toDomainModel()
    )
}