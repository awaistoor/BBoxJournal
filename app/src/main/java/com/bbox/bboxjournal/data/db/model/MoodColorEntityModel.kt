package com.bbox.bboxjournal.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bbox.bboxjournal.domain.model.MoodColorDomainModel

@Entity(tableName = "mood_color")
data class MoodColorEntityModel(
    @PrimaryKey @ColumnInfo("mood_color_id") val id: Int,
    val color: String
)

fun MoodColorEntityModel.toDomainModel(): MoodColorDomainModel {
    return MoodColorDomainModel(
        id = id,
        color = color
    )
}