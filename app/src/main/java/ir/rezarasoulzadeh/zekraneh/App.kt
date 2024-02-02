package ir.rezarasoulzadeh.zekraneh

import android.app.Application
import com.orhanobut.hawk.Hawk

class HappyApp : Application() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        initializeHawk()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     configs                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * initialize hawk with application context.
     */
    private fun initializeHawk() {
        Hawk.init(applicationContext).build()
    }

}