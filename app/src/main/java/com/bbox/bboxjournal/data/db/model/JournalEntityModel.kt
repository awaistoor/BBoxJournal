package com.bbox.bboxjournal.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbox.bboxjournal.domain.model.JournalDomainModel

/**
 * [JournalEntityModel]
 * Entity model for cache layer
 * PrimaryKey [id]
 */
@Entity(tableName = "journal")
data class JournalEntityModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val note: String,
    val dateTime: String,
    val moodColor: String
)

/**
 * [JournalEntityModel.toDomainModel]
 * An extension function to convert entity model to domain model
 * @return [JournalDomainModel]
 */
fun JournalEntityModel.toDomainModel() = JournalDomainModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor
    )

/**
 * [JournalDomainModel.toEntityModel]
 * An extension function to convert domain model to entity model
 * @return [JournalEntityModel]
 */
fun JournalDomainModel.toEntityModel() =  JournalEntityModel(
        id = id,
        note = note,
        dateTime = dateTime,
        moodColor = moodColor
    )