package ir.rezarasoulzadeh.zekraneh.utils.extensions

import android.view.View

/**
 * rotate the view to given angle with animation that's default duration is 300 milli seconds.
 */
fun View.rotate(destinationRotate: Float, duration: Long = 150) {
    animate().rotation(destinationRotate).duration = duration
}