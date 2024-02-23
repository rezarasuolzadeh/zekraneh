package ir.rezarasoulzadeh.zekraneh.utils.managers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants
import ir.rezarasoulzadeh.zekraneh.view.widget.SalavatWidget
import ir.rezarasoulzadeh.zekraneh.view.widget.TasbihatWidget
import ir.rezarasoulzadeh.zekraneh.view.widget.ZekrWidget

object IntentManager {

    /**
     * rate the app with CafeBazaar rate intent.
     */
    fun rateIntent(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.data = Uri.parse(context.resources.getString(R.string.bazaarStarLink))
            intent.setPackage(context.resources.getString(R.string.bazaarPackage))
            context.startActivity(intent)
        } catch (e: java.lang.Exception) {
            Toast.makeText(context, "ابتدا برنامه بازار را نصب کنید", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * share a simple text and that's title with every app can share.
     */
    fun shareTextIntent(context: Context, title: String, description: String) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, description)
            context.startActivity(Intent.createChooser(intent, title))
        } catch (e: Exception) {
            Toast.makeText(context, "خطایی در همرسانی پیش آمده است", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * send a broadcast intent to salavat widget to reset the salavat counter.
     */
    fun resetSalavatIntent(context: Context) {
        val intent = Intent(context, SalavatWidget::class.java)
        intent.action = Constants.RESET_SALAVAT
        context.sendBroadcast(intent)
    }

    /**
     * send a broadcast intent to zekr widget to reset the zekr counter.
     */
    fun resetZekrIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = Constants.RESET_ZEKR
        context.sendBroadcast(intent)
    }

    /**
     * send a broadcast intent to tasbihat widget to reset the tasbihat all counters.
     */
    fun resetTasbihatIntent(context: Context) {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = Constants.RESET_TASBIHAT
        context.sendBroadcast(intent)
    }

    /**
     * send a broadcast intent to salavat widget to change the salavat text color.
     */
    fun changeSalavatTextColorIntent(context: Context) {
        val intent = Intent(context, SalavatWidget::class.java)
        intent.action = Constants.COLOR
        context.sendBroadcast(intent)
    }

    /**
     * send a broadcast intent to zekr widget to change the zekr text color.
     */
    fun changeZekrTextColorIntent(context: Context) {
        val intent = Intent(context, ZekrWidget::class.java)
        intent.action = Constants.COLOR
        context.sendBroadcast(intent)
    }

    /**
     * send a broadcast intent to tasbihat widget to change the tasbihat text color.
     */
    fun changeTasbihatTextColorIntent(context: Context) {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = Constants.COLOR
        context.sendBroadcast(intent)
    }

}