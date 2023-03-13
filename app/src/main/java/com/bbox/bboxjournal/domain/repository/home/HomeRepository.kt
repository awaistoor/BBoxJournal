package com.bbox.bboxjournal.domain.repository.home

import com.bbox.bboxjournal.domain.model.home.NoteDomainModel

interface HomeRepository {
    suspend fun getAllNotes(): List<NoteDomainModel>
}