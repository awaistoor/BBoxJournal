package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import javax.inject.Inject

/**
 * [GetEntriesCountFromMapUseCase]
 * A use case to get count of entries from provided [Map]
 * The [invoke] function takes [Map] of [String] and [List] of [JournalDomainModel] as param and returns [Int]
 */
class GetEntriesCountFromMapUseCase @Inject constructor() {
    operator fun invoke(list: Map<String, List<JournalDomainModel>>): Int {
        var count = 0
        list.forEach {
            count += it.value.size
        }
        return count
    }
}