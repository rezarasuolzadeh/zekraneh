package ir.rezarasoulzadeh.zekraneh.view.activity

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import kotlinx.android.synthetic.main.activity_for_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var sharePrefs: SharedPrefs
    private var widgetId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharePrefs = SharedPrefs(this)

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

        exitButton.setOnClickListener {
            updateAllWidgets()
            finish()
        }

        sharePrefs = SharedPrefs(this)

    }

    private fun updateAllWidgets() {
        val appWidgetManager = AppWidgetManager.getInstance(this)

        val zekr = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                WidgetActivity::class.java
            )
        )
        WidgetActivity().onUpdate(this, appWidgetManager, zekr)

        val salavat = appWidgetManager.getAppWidgetIds(
            ComponentName(
                this,
                SalavatActivity::class.java
            )
        )
        SalavatActivity().onUpdate(this, appWidgetManager, salavat)
    }

}