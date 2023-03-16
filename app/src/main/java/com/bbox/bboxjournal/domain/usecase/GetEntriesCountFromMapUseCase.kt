package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import javax.inject.Inject

class GetEntriesCountFromMapUseCase @Inject constructor() {
    operator fun invoke(list: Map<String, List<JournalDomainModel>>): Int {
        var count = 0
        list.forEach {
            count += it.value.size
        }
        return count
    }
}