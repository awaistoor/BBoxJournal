package com.bbox.bboxjournal.data.db.dao

import androidx.room.*
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

@Dao
interface BBoxJournalDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJournal(journal: JournalEntityModel)

    @Query("SELECT * FROM journal")
    suspend fun getAllJournals(): List<JournalEntityModel>

    @Delete
    suspend fun deleteJournal(journal: JournalEntityModel)

}