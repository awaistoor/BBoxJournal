package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class GetAllJournalsUseCase @Inject constructor(private val journalRepository: JournalRepository) {
    suspend operator fun invoke() = journalRepository.getAllJournals()
}