package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import javax.inject.Inject

class GetCumulativeMoodColorUseCase @Inject constructor() {
    operator fun invoke(list: Map<String, List<JournalDomainModel>>): String {
        val array = arrayListOf<JournalDomainModel>()
        list.forEach { (_, value) ->
            array.addAll(value)
        }
        return array.groupingBy { it.moodColor }.eachCount().maxBy { it.value }.key
    }
}