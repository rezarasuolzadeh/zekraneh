package ir.rezarasoulzadeh.zekraneh.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.COLOR
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.RESET_SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.managers.DateManager
import ir.rezarasoulzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasoulzadeh.zekraneh.view.activity.HomeActivity

class SalavatWidget : AppWidgetProvider() {

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
                R.id.tvSalavatCounter,
                HawkManager.increaseSalavat().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, SalavatWidget::class.java),
                remoteViews
            )
        }
        if (RESET_SALAVAT == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_salavat)
            remoteViews.setTextViewText(
                R.id.tvSalavatCounter,
                HawkManager.getSalavat().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, SalavatWidget::class.java),
                remoteViews
            )
        }
        if (COLOR == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_salavat)
            remoteViews.setTextColor(
                R.id.tvSalavatTitle,
                context.resources.getColor(HawkManager.getTextColor().color)
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, SalavatWidget::class.java),
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
        views.setTextViewText(R.id.tvSalavatDay, DateManager.getTodayName())
        views.setTextViewText(R.id.tvSalavatCounter, HawkManager.getSalavat().toString())
        views.setTextColor(
            R.id.tvSalavatTitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setOnClickPendingIntent(
            R.id.tvSalavatCounter,
            updateSalavatIntent(
                context = context,
                action = SALAVAT,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvSalavatDay,
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
        return PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    /**
     * generate the specific pending intent to update salavat counter then return it.
     */
    private fun updateSalavatIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, SalavatWidget::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}