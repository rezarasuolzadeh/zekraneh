package ir.rezarasoulzadeh.zekraneh.view.activity

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.zekraneh.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        whiteStatusBar(window)
        setContentView(R.layout.activity_home)
    }

    private fun whiteStatusBar(window: Window) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}