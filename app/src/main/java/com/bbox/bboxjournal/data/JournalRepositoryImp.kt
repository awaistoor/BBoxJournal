package com.bbox.bboxjournal.data

import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.toDomainModel
import com.bbox.bboxjournal.data.db.model.toEntityModel
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class JournalRepositoryImp @Inject constructor(private val bBoxJournalDAO: BBoxJournalDAO) :
    JournalRepository {
    override suspend fun getAllJournals(): MutableList<JournalDomainModel> {
        return bBoxJournalDAO.getAllJournals().map { it.toDomainModel() }.toMutableList()
    }

    override suspend fun addJournal(journalDomainModel: JournalDomainModel) {
        bBoxJournalDAO.insertJournal(journalDomainModel.toEntityModel())
    }

    override suspend fun getJournalById(journalId: Int): JournalDomainModel {
        return bBoxJournalDAO.getJournalById(journalId).toDomainModel()
    }

    override suspend fun deleteJournal(journalId: Int) {
        bBoxJournalDAO.deleteJournal(journalId)
    }
}