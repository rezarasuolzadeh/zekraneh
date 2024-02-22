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
import ir.rezarasoulzadeh.zekraneh.utils.Constants.SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.DateManager
import ir.rezarasoulzadeh.zekraneh.utils.HawkManager


class SalavatActivity : AppWidgetProvider() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context = context,
                appWidgetManager = appWidgetManager,
                appWidgetId = appWidgetId
            )
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (SALAVAT == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_salavat)
            remoteViews.setTextViewText(
                R.id.salavatCounterTextView,
                HawkManager.increaseSalavat().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, SalavatActivity::class.java),
                remoteViews
            )
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                       configs                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * initialize the widget content or change them.
     */
    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val views = RemoteViews(context.packageName, R.layout.widget_salavat)
        views.setTextViewText(R.id.salavatDay, DateManager.getTodayName())
        views.setTextViewText(R.id.salavatCounterTextView, HawkManager.getSalavat().toString())
        views.setOnClickPendingIntent(
            R.id.salavatCounterTextView,
            updateSalavatIntent(
                context = context,
                action = SALAVAT,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.salavatDay,
            openHomeActivityIntent(
                context = context,
                appWidgetId = appWidgetId
            )
        )
        appWidgetManager.updateAppWidget(appWidgetId, views)
    }

    /**
     * generate the specific pending intent to open home activity then return it.
     */
    private fun openHomeActivityIntent(
        context: Context,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, HomeActivity::class.java)
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }

    /**
     * generate the specific pending intent to update salavat counter then return it.
     */
    private fun updateSalavatIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, SalavatActivity::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}