package com.bbox.bboxjournal.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

/**
 * [BBoxJournalDB]
 * A [RoomDatabase] class for defining the local DB for the app
 */
@Database(entities = [JournalEntityModel::class], version = 1)
abstract class BBoxJournalDB : RoomDatabase() {
    abstract fun getBBoxJournalDao(): BBoxJournalDAO
}