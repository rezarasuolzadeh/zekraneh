package ir.rezarasuolzadeh.zekraneh.utils.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * rotate the view to given angle with animation that's default duration is 300 milli seconds.
 */
fun View.rotate(destinationRotate: Float, duration: Long = 150) {
    animate().rotation(destinationRotate).duration = duration
}

/**
 * hide the soft keyboard.
 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}