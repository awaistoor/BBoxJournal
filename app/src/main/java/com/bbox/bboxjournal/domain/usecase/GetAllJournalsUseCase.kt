package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import com.bbox.bboxjournal.domain.repository.JournalRepository
import javax.inject.Inject

/**
 * [GetJournalByIdUseCase]
 * A usecase to get all journals from data source into group form
 * the group will be month and date
 * @param journalRepository as [JournalRepository]
 * @param convertDateTimeFormatUseCase as [ConvertDateTimeFormatUseCase]
 * the [invoke] function will return [HashMap] of [String] and [Map]
 */
class GetAllJournalsUseCase @Inject constructor(
    private val journalRepository: JournalRepository,
    private val convertDateTimeFormatUseCase: ConvertDateTimeFormatUseCase
) {
    suspend operator fun invoke(): HashMap<String, Map<String, List<JournalDomainModel>>> {
        // get all journals from repo
        val journalList = journalRepository.getAllJournals()
        // group the journals by month with provided month format e.g. March, 2023
        val journalMap: Map<String, List<JournalDomainModel>> =
            journalList.groupBy { convertDateTimeFormatUseCase.invoke(it.dateTime, "MMMM, yyyy") }
        // create a new hashmap
        val journalHashMap: HashMap<String, Map<String, List<JournalDomainModel>>> = hashMapOf()
        // group the each key into sub hashmap
        journalMap.forEach { (key, value) ->
            // group the values again over here with date format e.g. Tue, 15
            // add the group the hashmap
            journalHashMap[key] =
                value.groupBy { convertDateTimeFormatUseCase.invoke(it.dateTime, "EEE, dd") }
        }
        return journalHashMap
    }
}