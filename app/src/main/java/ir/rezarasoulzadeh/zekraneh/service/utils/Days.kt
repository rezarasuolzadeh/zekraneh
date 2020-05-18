package ir.rezarasoulzadeh.zekraneh.service.utils

import java.text.SimpleDateFormat
import java.util.*

class Days {

    fun getToday() : String {
        val simpleDateFormat = SimpleDateFormat("EEEE")
        val date = Date()
        return simpleDateFormat.format(date)
    }

    fun getTodayName(day : String) : String {
        return when (day) {
            "Saturday" -> "شنبه"
            "Sunday" -> "یکشنبه"
            "Monday" -> "دوشنبه"
            "Tuesday" -> "سه شنبه"
            "Wednesday" -> "چهارشنبه"
            "Thursday" -> "پنجشنبه"
            "Friday" -> "جمعه"
            else -> "جمعه"
        }
    }

}