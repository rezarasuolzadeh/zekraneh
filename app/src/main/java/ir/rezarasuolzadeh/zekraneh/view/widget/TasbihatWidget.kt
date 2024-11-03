package ir.rezarasuolzadeh.zekraneh.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import ir.rezarasuolzadeh.zekraneh.R
import ir.rezarasuolzadeh.zekraneh.base.BaseWidget
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.COLOR
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.RESET_TASBIHAT
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_AA
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_HA
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.TASBIHAT_SA
import ir.rezarasuolzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasuolzadeh.zekraneh.view.activity.HomeActivity

class TasbihatWidget : BaseWidget() {

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
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasbihat)
        when (intent.action) {
            TASBIHAT_AA -> {
                remoteViews.setTextViewText(
                    R.id.tvTasbihatAACounter,
                    HawkManager.increaseTasbihatAA().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
            }

            TASBIHAT_SA -> {
                remoteViews.setTextViewText(
                    R.id.tvTasbihatSACounter,
                    HawkManager.increaseTasbihatSA().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
            }

            TASBIHAT_HA -> {
                remoteViews.setTextViewText(
                    R.id.tvTasbihatHACounter,
                    HawkManager.increaseTasbihatHA().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
            }

            RESET_TASBIHAT -> {
                remoteViews.setTextViewText(
                    R.id.tvTasbihatAACounter,
                    HawkManager.getTasbihatAA().toString()
                )
                remoteViews.setTextViewText(
                    R.id.tvTasbihatSACounter,
                    HawkManager.getTasbihatSA().toString()
                )
                remoteViews.setTextViewText(
                    R.id.tvTasbihatHACounter,
                    HawkManager.getTasbihatHA().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
            }

            COLOR -> {
                remoteViews.setTextColor(
                    R.id.tvTasbihatAATitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                remoteViews.setTextColor(
                    R.id.tvTasbihatSATitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                remoteViews.setTextColor(
                    R.id.tvTasbihatHATitle,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, TasbihatWidget::class.java),
                    remoteViews
                )
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
        val views = RemoteViews(context.packageName, R.layout.widget_tasbihat)
        views.setTextViewText(R.id.tvTasbihatAACounter, HawkManager.getTasbihatAA().toString())
        views.setTextViewText(R.id.tvTasbihatSACounter, HawkManager.getTasbihatSA().toString())
        views.setTextViewText(R.id.tvTasbihatHACounter, HawkManager.getTasbihatHA().toString())
        views.setTextColor(
            R.id.tvTasbihatAATitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setTextColor(
            R.id.tvTasbihatSATitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setTextColor(
            R.id.tvTasbihatHATitle,
            context.resources.getColor(HawkManager.getTextColor().color)
        )
        views.setOnClickPendingIntent(
            R.id.tvTasbihatAACounter,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_AA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvTasbihatSACounter,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_SA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvTasbihatHACounter,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_HA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvTodayTasbihat,
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
     * generate the specific pending intent to update tasbihat counter then return it.
     */
    private fun updateTasbihatIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, TasbihatWidget::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}