package com.bbox.bboxjournal.domain.usecase

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * [GetCurrentDateTimeUseCase]
 * A use case to get the current datetime in ISO format
 * the [invoke] function returns formatted datetime as [String]
 */
class GetCurrentDateTimeUseCase @Inject constructor() {
    operator fun invoke(): String {
        val df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
        return df.format(Calendar.getInstance().time)
    }
}