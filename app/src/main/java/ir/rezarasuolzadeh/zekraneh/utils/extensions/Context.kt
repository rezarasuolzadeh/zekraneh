package ir.rezarasuolzadeh.zekraneh.utils.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log

/**
 * vibrate the phone with given specific duration.
 */
fun Context.vibratePhone(duration: Long = 25) {
    try {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    duration,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(duration)
        }
    } catch (e: Exception) {
        Log.e("Vibration Exception", e.message.toString())
    }
}