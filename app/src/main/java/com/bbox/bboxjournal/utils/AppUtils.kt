package com.bbox.bboxjournal.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

/**
 * [showToast]
 * a function to show toast anywhere in the application
 * @param context [Context]
 * @param message [String]
 */
fun showToast(context: Context?, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * [showSnackBar]
 * a function to show snack bar anywhere in the application
 * @param view [View]
 * @param message [String]
 */
fun showSnackBar(view: View, message: String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}