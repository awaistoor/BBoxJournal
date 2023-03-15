package com.bbox.bboxjournal.domain.repository

import com.bbox.bboxjournal.domain.model.JournalDomainModel

interface JournalRepository {
    suspend fun getAllJournals(): List<JournalDomainModel>
    suspend fun addJournal(journalDomainModel: JournalDomainModel)

    suspend fun getJournalById(journalId: Int): JournalDomainModel
    suspend fun deleteJournal(journalId: Int)
}