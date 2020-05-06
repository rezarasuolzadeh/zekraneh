package ir.rezarasoulzadeh.zekr

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews


/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in [ZekrActivityConfigureActivity]
 */
class ZekrActivity : AppWidgetProvider() {
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
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.zekr_activity)

    // Create intent pointing to ConfigurationActivity, in this example we are at ConfigurationActivity

    // Create intent pointing to ConfigurationActivity, in this example we are at ConfigurationActivity
    val configurationIntent =
        Intent(context, ZekrActivityConfigureActivity::class.java)
    // Create a extra giving the App Widget Id
    // Create a extra giving the App Widget Id
    configurationIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
    // Create a pending intent giving configurationIntent as parameter
    // Create a pending intent giving configurationIntent as parameter
    val configurationPendingIntent = PendingIntent.getActivity(
        context,
        0,
        configurationIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )
    // Setting onClick event that will lauch ConfigurationActivity
    // Setting onClick event that will lauch ConfigurationActivity
    views.setOnClickPendingIntent(R.id.appwidget_text, configurationPendingIntent)

    // Instruct the widget manager to update the widget
    appWidgetManager.updateAppWidget(appWidgetId, views)
}