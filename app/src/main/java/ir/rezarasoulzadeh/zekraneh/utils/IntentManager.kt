package ir.rezarasoulzadeh.zekraneh.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import ir.rezarasoulzadeh.zekraneh.R

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

}