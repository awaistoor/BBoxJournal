package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class DeleteJournalUseCase @Inject constructor(private val repository: JournalRepository) {
    suspend operator fun invoke(journalId: Int) = repository.deleteJournal(journalId)
}