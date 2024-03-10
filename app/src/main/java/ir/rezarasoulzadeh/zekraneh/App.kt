package ir.rezarasoulzadeh.zekraneh

import android.app.Application
import android.content.Intent
import android.content.IntentFilter
import com.orhanobut.hawk.Hawk
import ir.rezarasoulzadeh.zekraneh.utils.receiver.TimeChangedReceiver

class App : Application() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate() {
        super.onCreate()
        initializeHawk()
        registerTimeChangedReceiver()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     configs                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * register the time changed receiver class.
     */
    private fun registerTimeChangedReceiver() {
        registerReceiver(TimeChangedReceiver(), IntentFilter(Intent.ACTION_TIME_TICK))
    }

    /**
     * initialize hawk with application context.
     */
    private fun initializeHawk() {
        Hawk.init(applicationContext).build()
    }

}