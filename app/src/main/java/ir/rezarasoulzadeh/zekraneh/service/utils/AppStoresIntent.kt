package ir.rezarasoulzadeh.zekraneh.service.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import ir.rezarasoulzadeh.zekraneh.R
import java.lang.Exception

class AppStoresIntent(var context: Context) {

    // bazaar
    fun bazaarStar() {
        try {
            val intent = Intent(Intent.ACTION_EDIT)
            intent.data = Uri.parse(context.resources.getString(R.string.bazaarStarLink))
            intent.setPackage(context.resources.getString(R.string.bazaarPackage))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "ابتدا برنامه بازار رو نصب کنید", Toast.LENGTH_SHORT).show()
        }
    }

    fun bazaarDeveloper() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(context.resources.getString(R.string.bazaarDeveloperLink))
            intent.setPackage(context.resources.getString(R.string.bazaarPackage))
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "ابتدا برنامه بازار رو نصب کنید", Toast.LENGTH_SHORT).show()
        }
    }

}