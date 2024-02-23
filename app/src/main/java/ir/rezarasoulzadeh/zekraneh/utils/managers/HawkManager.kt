package ir.rezarasoulzadeh.zekraneh.utils.managers

import com.orhanobut.hawk.Hawk
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.COLOR
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.TASBIHAT_AA
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.TASBIHAT_HA
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.TASBIHAT_SA
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.ZEKR
import ir.rezarasoulzadeh.zekraneh.utils.enums.ColorType

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      tasbihat                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save tasbihatAA to hawk.
     */
    fun saveTasbihatAA(tasbihatAA: Int) = Hawk.put(TASBIHAT_AA, tasbihatAA)

    /**
     * get tasbihatAA from hawk.
     */
    fun getTasbihatAA(): Int = Hawk.get(TASBIHAT_AA, 0) ?: 0

    /**
     * save the increased tasbihatAA to hawk then return it.
     */
    fun increaseTasbihatAA(): Int {
        val currentTasbihatAA = getTasbihatAA()
        return if(currentTasbihatAA < 34) {
            saveTasbihatAA(tasbihatAA = currentTasbihatAA + 1)
            currentTasbihatAA + 1
        } else {
            currentTasbihatAA
        }
    }

    /**
     * save tasbihatSA to hawk.
     */
    fun saveTasbihatSA(tasbihatSA: Int) = Hawk.put(TASBIHAT_SA, tasbihatSA)

    /**
     * get tasbihatSA from hawk.
     */
    fun getTasbihatSA(): Int = Hawk.get(TASBIHAT_SA, 0) ?: 0

    /**
     * save the increased tasbihatSA to hawk then return it.
     */
    fun increaseTasbihatSA(): Int {
        val currentTasbihatSA = getTasbihatSA()
        return if(currentTasbihatSA < 33) {
            saveTasbihatSA(tasbihatSA = currentTasbihatSA + 1)
            currentTasbihatSA + 1
        } else {
            currentTasbihatSA
        }
    }

    /**
     * save tasbihatHA to hawk.
     */
    fun saveTasbihatHA(tasbihatHA: Int) = Hawk.put(TASBIHAT_HA, tasbihatHA)

    /**
     * get tasbihatHA from hawk.
     */
    fun getTasbihatHA(): Int = Hawk.get(TASBIHAT_HA, 0) ?: 0

    /**
     * save the increased tasbihatHA to hawk then return it.
     */
    fun increaseTasbihatHA(): Int {
        val currentTasbihatHA = getTasbihatHA()
        return if(currentTasbihatHA < 33) {
            saveTasbihatHA(tasbihatHA = currentTasbihatHA + 1)
            currentTasbihatHA + 1
        } else {
            currentTasbihatHA
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       color                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save widget text color to hawk.
     */
    fun saveTextColor(color: ColorType) = Hawk.put(COLOR, color)

    /**
     * get widget text color from hawk.
     */
    fun getTextColor(): ColorType = Hawk.get<ColorType>(COLOR, ColorType.WHITE) ?: ColorType.WHITE

}