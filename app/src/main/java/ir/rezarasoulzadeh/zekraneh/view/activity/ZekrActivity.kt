package ir.rezarasoulzadeh.zekraneh.view.activity

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RemoteViews
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.service.utils.Days
import ir.rezarasoulzadeh.zekraneh.service.utils.Prays
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs
import ir.rezarasoulzadeh.zekraneh.service.utils.Timer

class ZekrActivity : AppWidgetProvider() {

    private val MyOnClick = "myOnClickTag"

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateZekr(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val sharedPrefs = SharedPrefs(context)
        super.onDeleted(context, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            sharedPrefs.setCounter("0")
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val sharedPrefs = SharedPrefs(context!!)
        if (MyOnClick == intent!!.action) {
            val views = RemoteViews(context.packageName, R.layout.widget_zekr)
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getCounter()
            sharedPrefs.setCounter((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(R.id.counterTextView, (previousCounter.toInt() + 1).toString())
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, ZekrActivity::class.java), views
            )
            updateZekr(context, AppWidgetManager.getInstance(context), appWidgetId)
        }
    }

}

fun updateZekr(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val MyOnClick = "myOnClickTag"

    val views = RemoteViews(context.packageName, R.layout.widget_zekr)

    Timer.handleCountDownTimer(context, appWidgetManager, appWidgetId, "zekr")

    val sharedPrefs = SharedPrefs(context)

    val days = Days()
    val prays = Prays()

    val currentDay = sharedPrefs.getDay()

    val today = days.getToday()
    val todayName = days.getTodayName(today)
    val pray = prays.getPrays(today)

    sharedPrefs.setDay(todayName)
    sharedPrefs.setPray(pray)

    if (todayName != currentDay) {
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
    val intent = Intent(context, HomeActivity::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
    val pendIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.appwidget_text, pendIntent)
    //////////////////////////////

    views.setOnClickPendingIntent(
        R.id.counterTextView,
        getPendingSelfIntent(context, MyOnClick, appWidgetId)
    )

    val bundle = Bundle()
    bundle.putInt("appWidgetId", appWidgetId)
    intent.putExtras(bundle)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getPendingSelfIntent(context: Context?, action: String?, appWidgetId: Int): PendingIntent? {
    val intent = Intent(context, ZekrActivity::class.java)
    intent.action = action
    intent.putExtra("id", appWidgetId)
    return PendingIntent.getBroadcast(context, 0, intent, 0)
}