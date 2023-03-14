package com.bbox.bboxjournal.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbox.bboxjournal.domain.model.JournalDomainModel

@Entity(tableName = "journal")
data class JournalEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val note: String,
    val dateTime: String,
    val moodColor: String
)

fun JournalEntityModel.toDomainModel(): JournalDomainModel {
    return JournalDomainModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor
    )
}

fun JournalDomainModel.toEntityModel(): JournalEntityModel {
    return JournalEntityModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor
    )
}