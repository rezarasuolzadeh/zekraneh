package ir.rezarasuolzadeh.zekraneh.utils.managers

import com.orhanobut.hawk.Hawk
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.SALAVAT
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.SALAVAT_DAY
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_AA
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_HA
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_SA
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TEXT_COLOR
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.ZEKR
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.ZEKR_DAY
import ir.rezarasuolzadeh.zekraneh.utils.enums.ColorType
import ir.rezarasuolzadeh.zekraneh.utils.extensions.orZero

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
    fun getSalavat(): Int = Hawk.get(SALAVAT, 0).orZero()

    /**
     * save the increased salavat to hawk then return it.
     */
    fun increaseSalavat(): Int {
        val currentSalavat = getSalavat()
        saveSalavat(salavat = currentSalavat + 1)
        return currentSalavat + 1
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                    salavat day                                             //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save salavat day to hawk.
     */
    fun saveSalavatDay(day: String) = Hawk.put(SALAVAT_DAY, day)

    /**
     * get salavat day from hawk.
     */
    fun getSalavatDay(): String = Hawk.get(SALAVAT_DAY, "").orEmpty()

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
    fun getZekr(): Int = Hawk.get(ZEKR, 0).orZero()

    /**
     * save the increased zekr to hawk then return it.
     */
    fun increaseZekr(): Int {
        val currentZekr = getZekr()
        saveZekr(zekr = currentZekr + 1)
        return currentZekr + 1
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                      zekr day                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * save zekr day to hawk.
     */
    fun saveZekrDay(day: String) = Hawk.put(ZEKR_DAY, day)

    /**
     * get zekr day from hawk.
     */
    fun getZekrDay(): String = Hawk.get(ZEKR_DAY, "").orEmpty()

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
    fun getTasbihatAA(): Int = Hawk.get(TASBIHAT_AA, 0).orZero()

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
    fun getTasbihatSA(): Int = Hawk.get(TASBIHAT_SA, 0).orZero()

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
    fun getTasbihatHA(): Int = Hawk.get(TASBIHAT_HA, 0).orZero()

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
    fun saveTextColor(color: ColorType) = Hawk.put(TEXT_COLOR, color)

    /**
     * get widget text color from hawk.
     */
    fun getTextColor(): ColorType = Hawk.get<ColorType>(TEXT_COLOR, ColorType.WHITE) ?: ColorType.WHITE

}