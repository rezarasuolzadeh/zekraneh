package ir.rezarasoulzadeh.zekraneh.service.utils

import android.appwidget.AppWidgetManager
import android.content.Context
import android.os.CountDownTimer
import ir.rezarasoulzadeh.zekraneh.view.activity.updateAppWidget
import ir.rezarasoulzadeh.zekraneh.view.activity.updateSalavat
import java.util.*
import java.util.concurrent.TimeUnit

class Timer {

    companion object {
        @JvmStatic
        fun handleCountDownTimer(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int,
            type: Int
        ) {
            val current = Calendar.getInstance(TimeZone.getDefault())
            val nextDate =
                getNextDay()
            object : CountDownTimer(nextDate.timeInMillis - current.timeInMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    var hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                    //if 24:00:00 occurs?
                    if (hours > 24) {
                        hours %= 24
                    }
                }

                override fun onFinish() {
                    if(type == 1) {
                        updateAppWidget(
                            context,
                            appWidgetManager,
                            appWidgetId
                        )
                    }
                    if(type == 2) {
                        updateSalavat(
                            context,
                            appWidgetManager,
                            appWidgetId
                        )
                    }
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
}