package com.bbox.bboxjournal.data.db.dao

import androidx.room.*
import com.bbox.bboxjournal.data.db.model.NoteEntityModel

@Dao
interface BBoxJournalDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNote(note: NoteEntityModel)

    @Query("SELECT * FROM note")
    fun getAllNotes(): List<NoteEntityModel>

    @Delete
    fun deleteNote(note: NoteEntityModel)

}