package com.example.feature_login.presentation

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.feature_login.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackBar(message: String, binding: View) {
    val cancel = getString(R.string.cancel)
    Snackbar.make(
        binding,
        message,
        Snackbar.LENGTH_SHORT
    ).setAction(cancel) {}.show()
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}