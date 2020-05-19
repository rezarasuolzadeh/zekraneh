package ir.rezarasoulzadeh.zekraneh.service.utils

import java.util.*

class Days {

    fun getToday() : String {
        val c = Calendar.getInstance()
        val dayOfWeek = c[Calendar.DAY_OF_WEEK]
        return dayOfWeek.toString()
    }

    fun getTodayName(day : String) : String {
        return when (day) {
            "1" -> "یکشنبه"
            "2" -> "دوشنبه"
            "3" -> "سه شنبه"
            "4" -> "چهارشنبه"
            "5" -> "پنجشنبه"
            "6" -> "جمعه"
            "7" -> "شنبه"
            else -> "شنبه"
        }
    }

}