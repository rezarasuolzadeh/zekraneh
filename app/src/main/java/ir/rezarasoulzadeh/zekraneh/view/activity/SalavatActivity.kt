package ir.rezarasoulzadeh.zekraneh.view.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.Days
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import ir.rezarasoulzadeh.zekraneh.service.utils.Timer

class SalavatActivity : AppWidgetProvider() {

    private val MyOnClick = "myOnClickTag"

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateSalavat(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val sharedPrefs = SharedPrefs(context)
        super.onDeleted(context, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            sharedPrefs.setSalavat("0")
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val sharedPrefs = SharedPrefs(context!!)
        if (MyOnClick == intent!!.action) {
            val views = RemoteViews(context.packageName, R.layout.widget_for_salavat)
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getSalavat()
            sharedPrefs.setSalavat((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(
                R.id.salavatCounterTextView,
                (previousCounter.toInt() + 1).toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, SalavatActivity::class.java), views
            )
            updateSalavat(context, AppWidgetManager.getInstance(context), appWidgetId)
        }
    }

}

fun updateSalavat(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val MyOnClick = "myOnClickTag"

    val views = RemoteViews(context.packageName, R.layout.widget_for_salavat)

    Timer.handleCountDownTimer(context, appWidgetManager, appWidgetId, 2)

    val sharedPrefs = SharedPrefs(context)

    val days = Days()

    val currentDay = sharedPrefs.getDay()

    val today = days.getToday()
    val todayName = days.getTodayName(today)

    sharedPrefs.setDay(todayName)

    if (todayName != currentDay) {
        sharedPrefs.setSalavat("0")
        views.setTextViewText(R.id.salavatCounterTextView, "0")
        views.setTextViewText(R.id.salavatDay, todayName)
    }

    val dayText = sharedPrefs.getDay()
    val counterText = sharedPrefs.getSalavat()

    views.setTextViewText(R.id.salavatDay, dayText)
    views.setTextViewText(R.id.salavatCounterTextView, counterText)

    // open configuration activity
    val intent = Intent(context, HomeActivity::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
    val pendIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.salavatLayout, pendIntent)
    //////////////////////////////

    views.setOnClickPendingIntent(
        R.id.salavatCounterTextView,
        getPendingSalavat(context, MyOnClick, appWidgetId)
    )

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getPendingSalavat(context: Context?, action: String?, appWidgetId: Int): PendingIntent? {
    val intent = Intent(context, SalavatActivity::class.java)
    intent.action = action
    intent.putExtra("id", appWidgetId)
    return PendingIntent.getBroadcast(context, 0, intent, 0)
}