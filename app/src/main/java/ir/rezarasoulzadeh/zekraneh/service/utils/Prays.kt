package ir.rezarasoulzadeh.zekraneh.service.utils

class Prays {

    fun getPrays(day : String) : String {
        return when (day) {
            "Saturday" -> "یا رَبَّ العالَمین"
            "Sunday" -> "یا ذُالجَلالِ وَ الاِکرام"
            "Monday" -> "یا قاضیَ الحاجات"
            "Tuesday" -> "یا اَرحَمَ الرّاحِمین"
            "Wednesday" -> "یا حَیُّ یا قَیّوم "
            "Thursday" -> "لا اِلهَ الَّا اَللهُ المَلِکُ الحَقُّ المُبین"
            "Friday" -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
            else -> "اَللهُمَ صَلِّ عَلی مُحَمَّدٍ وَ آلِ مُحَمَّد"
        }
    }

}