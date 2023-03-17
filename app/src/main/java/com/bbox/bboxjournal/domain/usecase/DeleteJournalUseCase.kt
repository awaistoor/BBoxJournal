package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

/**
 * [DeleteJournalUseCase]
 * A usecase to delete the journal from data source
 * @param [repository] as [JournalRepository]
 * The [invoke] function takes [Int] as param
 */
class DeleteJournalUseCase @Inject constructor(private val repository: JournalRepository) {
    suspend operator fun invoke(journalId: Int) = repository.deleteJournal(journalId)
}