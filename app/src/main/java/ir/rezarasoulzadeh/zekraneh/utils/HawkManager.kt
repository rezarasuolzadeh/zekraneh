package ir.rezarasoulzadeh.zekraneh.utils

import com.orhanobut.hawk.Hawk
import ir.rezarasoulzadeh.zekraneh.utils.Constants.SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.Constants.ZEKR

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                        zekr                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save zekr to hawk.
     */
    fun saveZekr(zekr: Int) = Hawk.put(ZEKR, zekr)

    /**
     * get zekr from hawk.
     */
    fun getZekr(): Int = Hawk.get(ZEKR, 0) ?: 0

    /**
     * save the increased zekr to hawk then return it.
     */
    fun increaseZekr(): Int {
        val currentZekr = getZekr()
        saveZekr(zekr = currentZekr + 1)
        return currentZekr + 1
    }

}