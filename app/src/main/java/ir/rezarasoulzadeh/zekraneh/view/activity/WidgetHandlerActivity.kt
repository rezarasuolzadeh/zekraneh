package ir.rezarasoulzadeh.zekraneh.view.activity

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import java.lang.Exception

class WidgetHandlerActivity : Activity() {

    private lateinit var sharePrefs : SharedPrefs
    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        sharePrefs = SharedPrefs(this)

        setContentView(R.layout.widget_config_activity)

        findViewById<View>(R.id.exitButton).setOnClickListener {
            finish()
        }

        findViewById<View>(R.id.resetDayPrayButton).setOnClickListener {
            sharePrefs.setCounter("0")

            val appWidgetManager = AppWidgetManager.getInstance(this)
            updateAppWidget(
                this,
                appWidgetManager,
                appWidgetId
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

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras

        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        sharePrefs = SharedPrefs(this)

        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(this)
        updateAppWidget(
            this,
            appWidgetManager,
            appWidgetId
        )

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }
    }

}