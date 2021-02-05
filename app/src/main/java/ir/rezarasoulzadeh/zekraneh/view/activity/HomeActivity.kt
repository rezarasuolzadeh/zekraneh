package ir.rezarasoulzadeh.zekraneh.view.activity

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.AppStoresIntent
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import kotlinx.android.synthetic.main.activity_for_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var sharePrefs: SharedPrefs
    private lateinit var appStoresIntent: AppStoresIntent
    private var widgetId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        whiteStatusBar(window)

        sharePrefs = SharedPrefs(this)
        appStoresIntent = AppStoresIntent(this)

        val bundle = intent.extras

        this.widgetId = bundle!!.getInt("appWidgetId", -1)

        if (this.widgetId == -1) {
            finish()
        }

        setContentView(R.layout.activity_for_home)

        resetZekrButton.setOnClickListener {
            sharePrefs.setCounter("0")
            updateAllWidgets()
            finish()
        }

        resetSalavatButton.setOnClickListener {
            sharePrefs.setSalavat("0")
            updateAllWidgets()
            finish()
        }

        resetTasbihatButton.setOnClickListener {
            sharePrefs.setAA("0")
            sharePrefs.setHA("0")
            sharePrefs.setSA("0")
            updateAllWidgets()
            finish()
        }

        starButton.setOnClickListener {
            appStoresIntent.bazaarStar()
        }

        developerButton.setOnClickListener {
            appStoresIntent.bazaarDeveloper()
        }

        exitButton.setOnClickListener {
            updateAllWidgets()
            finish()
        }

//        sharePrefs = SharedPrefs(this)

    }

    fun updateAllWidgets() {
        val appWidgetManager = AppWidgetManager.getInstance(this)

        val zekr = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                ZekrActivity::class.java
            )
        )
        ZekrActivity().onUpdate(this, appWidgetManager, zekr)

        val salavat = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                SalavatActivity::class.java
            )
        )
        SalavatActivity().onUpdate(this, appWidgetManager, salavat)

        val tasbihat = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                TasbihatActivity::class.java
            )
        )
        TasbihatActivity().onUpdate(this, appWidgetManager, tasbihat)
    }

    private fun whiteStatusBar(window: Window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

}