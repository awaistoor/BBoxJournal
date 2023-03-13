package com.bbox.bboxjournal.data.db.dao

import androidx.room.*
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

@Dao
interface BBoxJournalDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertJournal(journal: JournalEntityModel)

    @Query("SELECT * FROM journal")
    fun getAllJournals(): List<JournalEntityModel>

    @Delete
    fun deleteJournal(journal: JournalEntityModel)

}