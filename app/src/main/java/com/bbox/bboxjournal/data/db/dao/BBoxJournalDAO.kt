package com.bbox.bboxjournal.data.db.dao

import androidx.room.*
import com.bbox.bboxjournal.data.db.model.JournalEntityModel

/**
 * [BBoxJournalDAO]
 * Interface class to handle all CURD operations on local DB
 */
@Dao
interface BBoxJournalDAO {
    /**
     * [insertJournal]
     * Insert a [JournalEntityModel] in to the DB
     * @param journal as
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertJournal(journal: JournalEntityModel)

    /**
     * [getAllJournals]
     * A function to return journals list from the DB with descending order on time
     * @return [List] of [JournalEntityModel]
     */
    @Query("SELECT * FROM journal ORDER BY dateTime DESC")
    suspend fun getAllJournals(): List<JournalEntityModel>

    /**
     * [deleteJournal]
     * Deletes a journal from the db
     * @param journalId as [Int]
     */
    @Query("DELETE FROM journal WHERE id =:journalId")
    suspend fun deleteJournal(journalId: Int)

    /**
     * [getJournalById]
     * A function to return a journal from db by providing its Id
     * @param journalId as [Int]
     * @return [JournalEntityModel]
     */
    @Query("SELECT * FROM journal WHERE id =:journalId")
    suspend fun getJournalById(journalId: Int): JournalEntityModel

}