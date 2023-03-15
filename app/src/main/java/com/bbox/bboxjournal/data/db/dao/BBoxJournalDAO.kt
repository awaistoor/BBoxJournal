package com.bbox.bboxjournal.data.db.dao

import androidx.room.*
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

@Dao
interface BBoxJournalDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJournal(journal: JournalEntityModel)

    @Query("SELECT * FROM journal")
    suspend fun getAllJournals(): List<JournalEntityModel>

    @Query("DELETE FROM journal WHERE id =:journalId")
    suspend fun deleteJournal(journalId: Int)

    @Query("SELECT * FROM journal WHERE id =:journalId")
    suspend fun getJournalById(journalId: Int): JournalEntityModel

}