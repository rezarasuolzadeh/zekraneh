package ir.rezarasoulzadeh.zekraneh.utils.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ir.rezarasoulzadeh.zekraneh.utils.managers.IntentManager

class TimeChangedReceiver: BroadcastReceiver() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            IntentManager.apply {
                checkSalavatDayIntent(context = it)
                checkZekrDayIntent(context = it)
            }
        }
    }

}