package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: JournalRepository) {
    suspend operator fun invoke(journalDomainModel: JournalDomainModel) =
        repository.addJournal(journalDomainModel)
}