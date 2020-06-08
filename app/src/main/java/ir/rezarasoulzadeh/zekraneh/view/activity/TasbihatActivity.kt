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
import ir.rezarasoulzadeh.zekraneh.service.utils.SharedPrefs

class TasbihatActivity : AppWidgetProvider() {

    private val AAClick = "AAClickTag"
    private val HAClick = "HAClickTag"
    private val SAClick = "SAClickTag"

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateTasbihat(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        val sharedPrefs = SharedPrefs(context)
        super.onDeleted(context, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            sharedPrefs.setAA("0")
            sharedPrefs.setHA("0")
            sharedPrefs.setSA("0")
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        val sharedPrefs = SharedPrefs(context!!)
        if (AAClick == intent!!.action) {
            val views = RemoteViews(context.packageName, R.layout.widget_for_tasbihat)
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getAA()
            sharedPrefs.setAA((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(R.id.AACounterTextView, (previousCounter.toInt() + 1).toString())
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java), views
            )
            updateTasbihat(context, AppWidgetManager.getInstance(context), appWidgetId)
        }
        if (HAClick == intent.action) {
            val views = RemoteViews(context.packageName, R.layout.widget_for_tasbihat)
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getHA()
            sharedPrefs.setHA((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(R.id.HACounterTextView, (previousCounter.toInt() + 1).toString())
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java), views
            )
            updateTasbihat(context, AppWidgetManager.getInstance(context), appWidgetId)
        }
        if (SAClick == intent.action) {
            val views = RemoteViews(context.packageName, R.layout.widget_for_tasbihat)
            val appWidgetId = intent.getIntExtra("id", 0)
            val previousCounter = sharedPrefs.getSA()
            sharedPrefs.setSA((previousCounter!!.toInt() + 1).toString())
            views.setTextViewText(R.id.SACounterTextView, (previousCounter.toInt() + 1).toString())
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java), views
            )
            updateTasbihat(context, AppWidgetManager.getInstance(context), appWidgetId)
        }
    }
}

internal fun updateTasbihat(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val AAClick = "AAClickTag"
    val HAClick = "HAClickTag"
    val SAClick = "SAClickTag"

    val views = RemoteViews(context.packageName, R.layout.widget_for_tasbihat)

    val sharedPrefs = SharedPrefs(context)

    val counterAA = sharedPrefs.getAA()
    val counterHA = sharedPrefs.getHA()
    val counterSA = sharedPrefs.getSA()

    views.setTextViewText(R.id.AACounterTextView, counterAA)
    views.setTextViewText(R.id.HACounterTextView, counterHA)
    views.setTextViewText(R.id.SACounterTextView, counterSA)

    // open configuration activity
    val intent = Intent(context, HomeActivity::class.java)
    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
    val pendIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    views.setOnClickPendingIntent(R.id.tasbihatLayout, pendIntent)
    //////////////////////////////

    views.setOnClickPendingIntent(
        R.id.AACounterTextView,
        getPendingTasbihat(context, AAClick, appWidgetId)
    )

    views.setOnClickPendingIntent(
        R.id.HACounterTextView,
        getPendingTasbihat(context, HAClick, appWidgetId)
    )

    views.setOnClickPendingIntent(
        R.id.SACounterTextView,
        getPendingTasbihat(context, SAClick, appWidgetId)
    )

    val bundle = Bundle()
    bundle.putInt("appWidgetId", appWidgetId)
    intent.putExtras(bundle)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}

fun getPendingTasbihat(context: Context?, action: String?, appWidgetId: Int): PendingIntent? {
    val intent = Intent(context, TasbihatActivity::class.java)
    intent.action = action
    intent.putExtra("id", appWidgetId)
    return PendingIntent.getBroadcast(context, 0, intent, 0)
}