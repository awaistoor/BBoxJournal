package com.bbox.bboxjournal.domain.repository

import com.bbox.bboxjournal.domain.model.JournalDomainModel

interface JournalRepository {
    suspend fun getAllJournals(): List<JournalDomainModel>
}