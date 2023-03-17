package com.bbox.bboxjournal.domain.usecase

import com.bbox.bboxjournal.domain.model.JournalDomainModel
import javax.inject.Inject

/**
 * [GetCumulativeMoodColorUseCase]
 * A use case to get the common color name in the provided [Map]
 * The [invoke] function takes [Map] of [String] and [List] of [JournalDomainModel] and returns [String]
 */
class GetCumulativeMoodColorUseCase @Inject constructor() {
    operator fun invoke(list: Map<String, List<JournalDomainModel>>): String {
        // create a new array of JournalDomainModel
        val array = arrayListOf<JournalDomainModel>()
        list.forEach { (_, value) ->
            // add all into the array
            array.addAll(value)
        }
        // group the array with color and then get the count for each entry
        // and then take the value of the color which as max count
        return array.groupingBy { it.moodColor }.eachCount().maxBy { it.value }.key
    }
}