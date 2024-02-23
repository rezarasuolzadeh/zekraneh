package ir.rezarasoulzadeh.zekraneh.utils

import java.util.Calendar

object DateManager {

    /**
     * find the the current day is which day of week then return it's persian name.
     */
    fun getTodayName(): String = when (Calendar.getInstance()[Calendar.DAY_OF_WEEK]) {
        1 -> "یکشنبه"
        2 -> "دوشنبه"
        3 -> "سه شنبه"
        4 -> "چهارشنبه"
        5 -> "پنجشنبه"
        6 -> "جمعه"
        7 -> "شنبه"
        else -> "شنبه"
    }

}