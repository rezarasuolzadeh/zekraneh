package ir.rezarasoulzadeh.zekraneh.view.activity

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs


class WidgetHandlerActivity : AppCompatActivity() {

    private lateinit var sharePrefs : SharedPrefs
    private var widgetId = -1

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        sharePrefs = SharedPrefs(this)

        val bundle = intent.extras

        if (bundle != null) {
            this.widgetId = bundle.getInt("appWidgetId", -1)
        }
        if (this.widgetId == -1) {
            finish()
        }

        setContentView(R.layout.widget_config_activity)

        findViewById<View>(R.id.exitButton).setOnClickListener {
            ////////////////// first ////////////////////
            val appWidgetManager = AppWidgetManager.getInstance(this)
            val zekr = appWidgetManager.getAppWidgetIds(
                ComponentName(
                    this,
                    WidgetActivity::class.java
                )
            )
            WidgetActivity().onUpdate(this, appWidgetManager, zekr)

            ////////////////// second ////////////////////
            val salavat = appWidgetManager.getAppWidgetIds(
                ComponentName(
                    this,
                    SalavatActivity::class.java
                )
            )
            SalavatActivity().onUpdate(this, appWidgetManager, salavat)
            finish()
        }

        findViewById<View>(R.id.resetDayPrayButton).setOnClickListener {
            sharePrefs.setCounter("0")

            val appWidgetManager = AppWidgetManager.getInstance(this)
            updateAppWidget(
                this,
                appWidgetManager,
                widgetId
            )

            finish()
        }

        findViewById<View>(R.id.developerButton).setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(this.resources.getString(R.string.bazaarDeveloperLink))
                intent.setPackage(this.resources.getString(R.string.bazaarPackage))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "ابتدا برنامه بازار رو نصب کنید", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        sharePrefs = SharedPrefs(this)

    }

}