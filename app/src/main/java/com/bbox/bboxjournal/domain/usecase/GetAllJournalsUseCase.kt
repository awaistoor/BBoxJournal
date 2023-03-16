package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

class GetAllJournalsUseCase @Inject constructor(
    private val journalRepository: JournalRepository,
    private val convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase
) {
    suspend operator fun invoke(): HashMap<String, Map<String, List<JournalDomainModel>>> {
        val journalList = journalRepository.getAllJournals()
        val journalMap: Map<String, List<JournalDomainModel>> =
            journalList.groupBy { convertDateTimeFormatUseCase.invoke(it.dateTime, "MMMM, yyyy") }
        val journalHashMap: HashMap<String, Map<String, List<JournalDomainModel>>> = hashMapOf()
        journalMap.forEach { (key, value) ->
            journalHashMap[key] =
                value.groupBy { convertDateTimeFormatUseCase.invoke(it.dateTime, "EEE, dd") }
        }
        return journalHashMap
    }
}