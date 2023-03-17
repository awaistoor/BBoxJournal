package com.bbox.bboxjournal.utils

import android.view.View

/**
 * [View.makeGone]
 * a function to make visibility of any view to [View.GONE]
 */
fun View.makeGone() {
    this.visibility = View.GONE
}

/**
 * [View.makeVisible]
 * a function to make visibility of any view to [View.VISIBLE]
 */
fun View.makeVisible() {
    this.visibility = View.VISIBLE
}