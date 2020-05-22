package ir.rezarasoulzadeh.zekraneh.service.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private val sharedPreference: SharedPreferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    // KEYS
    private val prayKey: String = "prayKey"
    private val dayKey: String = "dayKey"
    private val counterKey: String = "counterKey"
    private val salavatKey: String = "salavatKey"

    // pray functions
    fun setPray(pray: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(prayKey, pray)
        editor.apply()
    }

    fun getPray(): String? {
        return sharedPreference.getString(prayKey, null)
    }


    // day functions
    fun setDay(day: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(dayKey, day)
        editor.apply()
    }

    fun getDay(): String? {
        return sharedPreference.getString(dayKey, null)
    }


    // counter functions
    fun setCounter(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(counterKey, counter)
        editor.apply()
    }

    fun getCounter(): String? {
        return sharedPreference.getString(counterKey, "0")
    }

    // counter functions
    fun setSalavat(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(salavatKey, counter)
        editor.apply()
    }

    fun getSalavat(): String? {
        return sharedPreference.getString(salavatKey, "0")
    }

}