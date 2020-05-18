package ir.rezarasoulzadeh.zekr.view.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import ir.rezarasoulzadeh.zekr.R
import ir.rezarasoulzadeh.zekr.service.utils.Days
import ir.rezarasoulzadeh.zekr.service.utils.Prays
import ir.rezarasoulzadeh.zekr.service.utils.SharedPrefs
import ir.rezarasoulzadeh.zekr.service.utils.Timer

class WidgetActivity : AppWidgetProvider() {

    private val MyOnClick = "myOnClickTag"

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.

        val sharedPrefs = SharedPrefs(context)

        super.onDeleted(context, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            sharedPrefs.setCounter("0")
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        val sharedPrefs = SharedPrefs(context!!)

        if (MyOnClick == intent!!.action) {
            val views = RemoteViews(
                context.packageName,
                R.layout.widget_activity
            )
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getCounter()
            sharedPrefs.setCounter((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(R.id.counterTextView, (previousCounter.toInt() + 1).toString())
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, WidgetActivity::class.java), views
            )
            updateAppWidget(
                context,
                AppWidgetManager.getInstance(context),
                appWidgetId
            )
        }
    }

}

fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val MyOnClick = "myOnClickTag"

    val views = RemoteViews(context.packageName, R.layout.widget_activity)

    Timer.handleCountDownTimer(context, appWidgetManager, appWidgetId)

    val sharedPrefs = SharedPrefs(context)

    val days = Days()
    val prays = Prays()

    val currentDay = sharedPrefs.getDay()

    val today = days.getToday()
    val todayName = days.getTodayName(today)
    val pray = prays.getPrays(today)

    sharedPrefs.setDay(todayName)
    sharedPrefs.setPray(pray)

    if(todayName != currentDay) {
        sharedPrefs.setCounter("0")
        views.setTextViewText(R.id.counterTextView, "0")
        views.setTextViewText(R.id.prayTextView, pray)
        views.setTextViewText(R.id.appwidget_text, todayName)
    }

    val dayText = sharedPrefs.getDay()
    val prayText = sharedPrefs.getPray()
    val counterText = sharedPrefs.getCounter()

    views.setTextViewText(R.id.appwidget_text, dayText)
    views.setTextViewText(R.id.prayTextView, prayText)
    views.setTextViewText(R.id.counterTextView, counterText)

    // open configuration activity
    val intent = Intent(context, WidgetHandlerActivity::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
    val pendIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.prayLayout, pendIntent)
    //////////////////////////////

    views.setOnClickPendingIntent(R.id.counterTextView, getPendingSelfIntent(context, MyOnClick, appWidgetId));

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getPendingSelfIntent(context: Context?, action: String?, appWidgetId: Int): PendingIntent? {
    val intent = Intent(context, WidgetActivity::class.java)
    intent.action = action
    intent.putExtra("id", appWidgetId)
    return PendingIntent.getBroadcast(context, 0, intent, 0)
}