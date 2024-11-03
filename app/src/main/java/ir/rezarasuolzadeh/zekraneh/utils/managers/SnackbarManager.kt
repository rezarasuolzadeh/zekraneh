package ir.rezarasuolzadeh.zekraneh.utils.managers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ir.rezarasuolzadeh.zekraneh.databinding.ViewSnackbarBinding

object SnackbarManager {

    /**
     * show snackbar message in current given view.
     */
    @SuppressLint("RestrictedApi")
    fun showSnackbar(context: Context, view: View, message: String) {
        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
        val customSnackView = ViewSnackbarBinding.inflate(LayoutInflater.from(context)).apply {
            tvMessage.text = message
        }
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        (snackbar.view as Snackbar.SnackbarLayout).apply {
            setPadding(0, 0, 0, 120)
            addView(customSnackView.root, 0)
        }
        snackbar.show()
    }

}