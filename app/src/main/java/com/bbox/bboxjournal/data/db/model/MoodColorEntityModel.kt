package com.bbox.bboxjournal.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_color")
data class MoodColorEntityModel(
    @PrimaryKey @ColumnInfo("mood_color_id") val id: Int,
    val color: String
)