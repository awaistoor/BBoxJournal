package com.bbox.bboxjournal.presentation.model

import androidx.annotation.ColorRes
import com.bbox.bboxjournal.R

/**
 * [MoodColorUiModel]
 * An enum class for predefined mood color for the Journals
 */
enum class MoodColorUiModel(@ColorRes val colorCode: Int) {
    GREEN(R.color.green_500), YELLOW(R.color.yellow_500), RED(R.color.red_500)
}



