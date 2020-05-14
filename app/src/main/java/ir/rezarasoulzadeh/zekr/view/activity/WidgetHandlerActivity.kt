package ir.rezarasoulzadeh.zekr.view.activity

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import ir.rezarasoulzadeh.zekr.R
import ir.rezarasoulzadeh.zekr.service.utils.Days
import ir.rezarasoulzadeh.zekr.service.utils.Prays


class WidgetHandlerActivity : Activity() {

    private var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID
    private lateinit var appWidgetText: EditText
    private val days = Days()
    private val prays = Prays()

    private var onClickListener = View.OnClickListener {
        val context = this@WidgetHandlerActivity

        // When the button is clicked, store the string locally
        val widgetText = appWidgetText.text.toString()
        saveDayPref(
            context,
            appWidgetId,
            widgetText
        )

        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(context)
        updateAppWidget(
            context,
            appWidgetManager,
            appWidgetId
        )

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(RESULT_OK, resultValue)
        finish()
    }

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED)

        setContentView(R.layout.widget_config_activity)
        appWidgetText = findViewById<View>(R.id.appwidget_text_id) as EditText
        findViewById<View>(R.id.add_button).setOnClickListener(onClickListener)

        val today = days.getToday()
        val todayName = days.getTodayName(today)
        val pray = prays.getPrays(today)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras

        if (extras != null) {
            appWidgetId = extras.getInt(
                AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID
            )
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        appWidgetText.setText(
            loadDayPref(
                this@WidgetHandlerActivity,
                appWidgetId
            )
        )
    }


}

private const val PREFS_NAME = "WeekPraysSharedPreferences"
private const val PREF_DAY_KEY = "day"
private const val PREF_PRAY_KEY = "pray"
private const val PREF_COUNTER_KEY = "counter"


// days shared preferences
internal fun saveDayPref(context: Context, appWidgetId: Int, text: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putString(PREF_DAY_KEY + appWidgetId, text)
    prefs.apply()
}

internal fun loadDayPref(context: Context, appWidgetId: Int): String {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    val titleValue = prefs.getString(PREF_DAY_KEY + appWidgetId, null)
    return titleValue ?: context.getString(R.string.defaultTitle)
}

internal fun deleteDayPref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(PREF_DAY_KEY + appWidgetId)
    prefs.apply()
}


// pray shared preferences
internal fun savePrayPref(context: Context, appWidgetId: Int, text: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putString(PREF_PRAY_KEY + appWidgetId, text)
    prefs.apply()
}

internal fun loadPrayPref(context: Context, appWidgetId: Int): String {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    val titleValue = prefs.getString(PREF_PRAY_KEY + appWidgetId, null)
    return titleValue ?: context.getString(R.string.defaultTitle)
}

internal fun deletePrayPref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(PREF_PRAY_KEY + appWidgetId)
    prefs.apply()
}


// counter shared preferences
internal fun saveCounterPref(context: Context, appWidgetId: Int, text: String) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.putString(PREF_COUNTER_KEY + appWidgetId, text)
    prefs.apply()
}

internal fun loadCounterPref(context: Context, appWidgetId: Int): String {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0)
    val titleValue = prefs.getString(PREF_COUNTER_KEY + appWidgetId, null)
    return titleValue ?: context.getString(R.string.defaultTitle)
}

internal fun deleteCounterPref(context: Context, appWidgetId: Int) {
    val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
    prefs.remove(PREF_COUNTER_KEY + appWidgetId)
    prefs.apply()
}