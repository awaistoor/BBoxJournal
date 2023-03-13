package com.bbox.bboxjournal.data

import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.toDomainModel
import com.bbox.bboxjournal.domain.model.home.NoteDomainModel
import com.bbox.bboxjournal.domain.repository.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(private val bBoxJournalDAO: BBoxJournalDAO) :
    HomeRepository {
    override suspend fun getAllNotes(): List<NoteDomainModel> {
        return bBoxJournalDAO.getAllNotes().map { it.toDomainModel() }
    }
}