package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

/**
 * [GetJournalByIdUseCase]
 * A use case to get a specific Journal from data source by providing its ID
 * @param repository as [JournalRepository]
 * The [invoke] function takes [Int] as param and return JournalDomainModel
 */
class GetJournalByIdUseCase @Inject constructor(private val repository: JournalRepository) {
    suspend operator fun invoke(journalId: Int) = repository.getJournalById(journalId)
}