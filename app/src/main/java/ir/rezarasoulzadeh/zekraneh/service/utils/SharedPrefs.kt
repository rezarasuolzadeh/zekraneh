package ir.rezarasoulzadeh.zekraneh.service.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private val sharedPreference: SharedPreferences = context.getSharedPreferences("sharedPreference", Context.MODE_PRIVATE)

    // KEYS
    private val prayKey: String = "prayKey"
    private val dayKey: String = "dayKey"
    private val daySalavatKey: String = "daySalavatKey"
    private val counterKey: String = "counterKey"
    private val salavatKey: String = "salavatKey"
    private val AAKey: String = "AAKey"
    private val HAKey: String = "HAKey"
    private val SAKey: String = "SAKey"

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

    // day salavat functions
    fun setSalavtDay(day: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(daySalavatKey, day)
        editor.apply()
    }

    fun getSalavtDay(): String? {
        return sharedPreference.getString(daySalavatKey, null)
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

    // salavat functions
    fun setSalavat(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(salavatKey, counter)
        editor.apply()
    }

    fun getSalavat(): String? {
        return sharedPreference.getString(salavatKey, "0")
    }

    // AA functions
    fun setAA(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(AAKey, counter)
        editor.apply()
    }

    fun getAA(): String? {
        return sharedPreference.getString(AAKey, "0")
    }

    // HA functions
    fun setHA(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(HAKey, counter)
        editor.apply()
    }

    fun getHA(): String? {
        return sharedPreference.getString(HAKey, "0")
    }

    // SA functions
    fun setSA(counter: String) {
        val editor: SharedPreferences.Editor = sharedPreference.edit()
        editor.putString(SAKey, counter)
        editor.apply()
    }

    fun getSA(): String? {
        return sharedPreference.getString(SAKey, "0")
    }

}