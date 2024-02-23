package ir.rezarasoulzadeh.zekraneh.utils.managers

import java.util.Calendar

object ZekrManager {

    /**
     * find the the current day is which day of week then return it's pray sentence.
     */
    fun getTodayZekr(): String = when (Calendar.getInstance()[Calendar.DAY_OF_WEEK]) {
        1 -> "یا ذوالجَلالِ وَ الاِکرام"
        2 -> "یا قاضیَ الحاجات"
        3 -> "یا اَرحَمَ الرّاحِمین"
        4 -> "یا حَیُّ یا قَیّوم "
        5 -> "لا اِلهَ الَّا اَللهُ المَلِکُ الحَقُّ المُبین"
        6 -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
        7 -> "یا رَبَّ العالَمین"
        else -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
    }

}