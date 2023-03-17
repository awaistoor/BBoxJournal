package com.bbox.bboxjournal.data

import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.toDomainModel
import com.bbox.bboxjournal.data.db.model.toEntityModel
import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

/**
 * [JournalRepositoryImp]
 * Repository class to handle all repo based functions for Journals
 * @param [bBoxJournalDAO] as [BBoxJournalDAO]
 * NOTE: if we have api service, we can add it is as a data source over here and we can implement its functions as well
 *      and the logic to decide from which data source should data come also can be define here.
 */
class JournalRepositoryImp @Inject constructor(private val bBoxJournalDAO: BBoxJournalDAO) :
    JournalRepository {

    /**
     * [getAllJournals]
     * A function to get All journals from the data layer
     * @return [List] of [JournalDomainModel]
     */
    override suspend fun getAllJournals(): List<JournalDomainModel> {
        return bBoxJournalDAO.getAllJournals().map { it.toDomainModel() }
    }

    /**
     * [addJournal]
     * A function to add journal into data layer
     * @param journalDomainModel as [JournalDomainModel]
     */
    override suspend fun addJournal(journalDomainModel: JournalDomainModel) {
        bBoxJournalDAO.insertJournal(journalDomainModel.toEntityModel())
    }

    /**
     * [getJournalById]
     * A function to get a specific journal form data layer
     * @param journalId as [Int]
     * @return [JournalDomainModel]
     */
    override suspend fun getJournalById(journalId: Int): JournalDomainModel {
        return bBoxJournalDAO.getJournalById(journalId).toDomainModel()
    }

    /**
     * [deleteJournal]
     * A function to delete a specific journal from data layer
     * @param journalId as [Int]
     */
    override suspend fun deleteJournal(journalId: Int) {
        bBoxJournalDAO.deleteJournal(journalId)
    }
}