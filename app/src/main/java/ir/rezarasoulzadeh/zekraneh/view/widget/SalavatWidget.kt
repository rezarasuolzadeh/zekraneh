package ir.rezarasoulzadeh.zekraneh.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import ir.rezarasoulzadeh.zekraneh.R
import ir.rezarasoulzadeh.zekraneh.base.BaseWidget
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.CHECK_DAY
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.COLOR
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.RESET_SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.constant.Constants.SALAVAT
import ir.rezarasoulzadeh.zekraneh.utils.managers.DateManager
import ir.rezarasoulzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasoulzadeh.zekraneh.view.activity.HomeActivity

class SalavatWidget : BaseWidget() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onAfterUpdate(
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

    override fun onAfterOptionChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        updateAppWidget(
            context = context,
            appWidgetManager = appWidgetManager,
            appWidgetId = appWidgetId
        )
    }

    override fun onAfterReceive(context: Context, intent: Intent) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_salavat)
        when (intent.action) {
            SALAVAT -> {
                val todayName = DateManager.getTodayName()
                val savedDay = HawkManager.getSalavatDay()
                if (todayName != savedDay) {
                    HawkManager.saveSalavat(salavat = 0)
                    HawkManager.saveSalavatDay(day = DateManager.getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvSalavatDay, DateManager.getTodayName())
                } else {
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.increaseSalavat().toString()
                    )
                }
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, SalavatWidget::class.java),
                    remoteViews
                )
            }

            RESET_SALAVAT -> {
                remoteViews.setTextViewText(
                    R.id.tvSalavatCounter,
                    HawkManager.getSalavat().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, SalavatWidget::class.java),
                    remoteViews
                )
            }

            COLOR -> {
                remoteViews.setTextColor(
                    R.id.tvSalavatTitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, SalavatWidget::class.java),
                    remoteViews
                )
            }

            CHECK_DAY -> {
                val todayName = DateManager.getTodayName()
                val savedDay = HawkManager.getSalavatDay()
                if (todayName != savedDay) {
                    HawkManager.saveSalavat(salavat = 0)
                    HawkManager.saveSalavatDay(day = DateManager.getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvSalavatCounter,
                        HawkManager.getSalavat().toString()
                    )
                    remoteViews.setTextViewText(R.id.tvSalavatDay, DateManager.getTodayName())
                    AppWidgetManager.getInstance(context).updateAppWidget(
                        ComponentName(context, SalavatWidget::class.java),
                        remoteViews
                    )
                }
            }
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
        HawkManager.saveSalavatDay(day = DateManager.getTodayName())
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
                action = SALAVAT
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
        action: String?
    ): PendingIntent? {
        val intent = Intent(context, SalavatWidget::class.java)
        intent.action = action
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}