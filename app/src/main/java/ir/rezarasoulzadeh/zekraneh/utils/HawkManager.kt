package ir.rezarasoulzadeh.zekraneh.utils

import com.orhanobut.hawk.Hawk
import ir.rezarasoulzadeh.zekraneh.utils.Constants.SALAVAT

object HawkManager {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      salavat                                               //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save salavat to hawk.
     */
    fun saveSalavat(salavat: Int) = Hawk.put(SALAVAT, salavat)

    /**
     * get salavat from hawk.
     */
    fun getSalavat(): Int = Hawk.get(SALAVAT, 0) ?: 0

    /**
     * save the increased salavat to hawk then return it.
     */
    fun increaseSalavat(): Int {
        val currentSalavat = getSalavat()
        saveSalavat(salavat = currentSalavat + 1)
        return currentSalavat + 1
    }

}