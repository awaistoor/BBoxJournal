package com.bbox.bboxjournal.domain.usecase

import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * [ConvertDateTimeFormatUseCase]
 * A use case to convert ISO date format into any provided date/time format
 * The [invoke] function takes two [String] as params and returns a [String]
 * @throws [IllegalArgumentException]
 */
class ConvertDateTimeFormatUseCase @Inject constructor() {
    operator fun invoke(originalDateTime: String, format: String): String {
        val originalFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        val convertedFormat = SimpleDateFormat(format, Locale.getDefault())
        val convertedDate = originalFormat.parse(originalDateTime)
            ?: throw IllegalArgumentException("Wrong format for the date")
        return convertedFormat.format(convertedDate)
    }
}