package com.bbox.bboxjournal.data

import com.bbox.bboxjournal.data.db.dao.BBoxJournalDAO
import com.bbox.bboxjournal.data.db.model.JournalEntityModel
import com.bbox.bboxjournal.data.db.model.toDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class JournalRepositoryImpTest {

    private lateinit var SUT: JournalRepositoryImp

    @Mock
    private lateinit var bBoxJournalDAO: BBoxJournalDAO

    @Before
    fun setup() {
        SUT = JournalRepositoryImp(bBoxJournalDAO = bBoxJournalDAO)
    }

    @Test
    fun testGetAllJournals() = runBlocking {
        Mockito.`when`(bBoxJournalDAO.getAllJournals()).thenReturn(getDummyJournalsList())
        Assert.assertEquals(
            bBoxJournalDAO.getAllJournals().map { it.toDomainModel() },
            SUT.getAllJournals()
        )
    }

    @Test
    fun testAddJournal() = runBlocking {
        Mockito.`when`(bBoxJournalDAO.insertJournal(getDummyJournal())).thenReturn(Unit)
        SUT.addJournal(getDummyJournal().toDomainModel())
        Mockito.verify(bBoxJournalDAO).insertJournal(getDummyJournal())
    }

    @Test
    fun testGetJournalById() = runBlocking {
        Mockito.`when`(bBoxJournalDAO.getJournalById(getDummyJournalId()))
            .thenReturn(getDummyJournal())
        Assert.assertEquals(
            bBoxJournalDAO.getJournalById(getDummyJournalId()).toDomainModel(),
            SUT.getJournalById(getDummyJournalId())
        )
    }

    @Test
    fun testDeleteJournal() = runBlocking {
        Mockito.`when`(bBoxJournalDAO.deleteJournal(getDummyJournalId())).thenReturn(Unit)
        SUT.deleteJournal(getDummyJournalId())
        Mockito.verify(bBoxJournalDAO).deleteJournal(getDummyJournalId())
    }

    private fun getDummyJournalId(): Int = 0

    private fun getDummyJournalsList(): List<JournalEntityModel> {
        return listOf(getDummyJournal())
    }

    private fun getDummyJournal(): JournalEntityModel {
        return JournalEntityModel(
            id = 0,
            note = "",
            dateTime = "",
            moodColor = ""
        )
    }
}