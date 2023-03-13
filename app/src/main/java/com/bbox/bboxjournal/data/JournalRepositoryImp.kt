package com.bbox.bboxjournal.data

import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.toDomainModel
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class JournalRepositoryImp @Inject constructor(private val bBoxJournalDAO: BBoxJournalDAO) :
    JournalRepository {
    override suspend fun getAllJournals(): List<JournalDomainModel> {
        return bBoxJournalDAO.getAllJournals().map { it.toDomainModel() }
    }
}