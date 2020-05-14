package ir.rezarasoulzadeh.zekr.service.utils

import android.appwidget.AppWidgetManager
import android.content.Context
import android.os.CountDownTimer
import ir.rezarasoulzadeh.zekr.view.activity.updateAppWidget
import java.util.*
import java.util.concurrent.TimeUnit

class Timer {

    fun handleCountDownTimer(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val current = Calendar.getInstance(TimeZone.getDefault())
        val nextDate = getNextDay()
        object : CountDownTimer(nextDate.timeInMillis - current.timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                //if 24:00:00 occurs?
                if (hours > 24) {
                    hours %= 24
                }
            }

            override fun onFinish() {
                updateAppWidget(context, appWidgetManager, appWidgetId)
            }
        }.start()
    }

    private fun getNextDay(): Calendar {
        return Calendar.getInstance(TimeZone.getDefault()).apply {
            add(Calendar.DAY_OF_MONTH, 1)
            set(
                get(Calendar.YEAR),
                get(Calendar.MONTH),
                get(Calendar.DATE),
                0,
                0,
                0
            )
        }
    }

}