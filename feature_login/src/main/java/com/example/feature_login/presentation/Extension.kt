package com.example.feature_login.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.RESULT_UNCHANGED_SHOWN
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message: String, actionText: String, action: (() -> Unit)? = null) {
    Snackbar.make(
        requireView(),
        message,
        Snackbar.LENGTH_SHORT
    ).setAction(actionText) { action?.invoke() }
        .show()
}

fun hideKeyboard(view: View, context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, RESULT_UNCHANGED_SHOWN)
}
