package com.bbox.bboxjournal.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.MoodColorEntityModel
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

@Database(entities = [JournalEntityModel::class, MoodColorEntityModel::class], version = 1)
abstract class BBoxJournalDB : RoomDatabase() {
    abstract fun getBBoxJournalDao(): BBoxJournalDAO
}