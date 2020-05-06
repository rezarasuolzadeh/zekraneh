package ir.rezarasoulzadeh.zekr

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

class WidgetActivity : AppWidgetProvider() {

    var counter = 0

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        // When the user deletes the widget, delete the preference associated with it.
        for (appWidgetId in appWidgetIds) {
            deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = loadTitlePref(context, appWidgetId)
    val views = RemoteViews(context.packageName, R.layout.widget_activity)
//    views.setTextViewText(R.id.appwidget_text, widgetText)
    val configurationIntent = Intent(context, WidgetHandlerActivity::class.java)
    configurationIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    val configurationPendingIntent = PendingIntent.getActivity(
        context,
        0,
        configurationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    views.setOnClickPendingIntent(R.id.appwidget_text, configurationPendingIntent)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}