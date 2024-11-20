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
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.CHECK_DAY
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.COLOR
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.CUSTOM_ZEKR
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.CUSTOM_ZEKR_TITLE
import ir.rezarasuolzadeh.zekraneh.utils.constant.Constants.RESET_CUSTOM_ZEKR
import ir.rezarasuolzadeh.zekraneh.utils.managers.DateManager
import ir.rezarasuolzadeh.zekraneh.utils.managers.HawkManager
import ir.rezarasuolzadeh.zekraneh.view.activity.HomeActivity

class CustomZekrWidget : BaseWidget() {

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
        val remoteViews = RemoteViews(context.packageName, R.layout.widget_custom_zekr)
        when (intent.action) {
            CUSTOM_ZEKR -> {
                val todayName = DateManager.getTodayName()
                val savedDay = HawkManager.getZekrDay()
                if (todayName != savedDay) {
                    HawkManager.saveCustomZekr(customZekr = 0)
                    HawkManager.saveZekrDay(day = DateManager.getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvCustomZekrCounter,
                        HawkManager.getCustomZekr().toString()
                    )
                } else {
                    remoteViews.setTextViewText(
                        R.id.tvCustomZekrCounter,
                        HawkManager.increaseCustomZekr().toString()
                    )
                }
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, CustomZekrWidget::class.java),
                    remoteViews
                )
            }

            RESET_CUSTOM_ZEKR -> {
                remoteViews.setTextViewText(
                    R.id.tvCustomZekrCounter,
                    HawkManager.getCustomZekr().toString()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, CustomZekrWidget::class.java),
                    remoteViews
                )
            }

            CUSTOM_ZEKR_TITLE -> {
                remoteViews.setTextViewText(
                    R.id.tvCustomZekr,
                    HawkManager.getCustomZekrTitle()
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, CustomZekrWidget::class.java),
                    remoteViews
                )
            }

            COLOR -> {
                remoteViews.setTextColor(
                    R.id.tvCustomZekr,
                    context.resources.getColor(HawkManager.getTextColor().color)
                )
                AppWidgetManager.getInstance(context).updateAppWidget(
                    ComponentName(context, CustomZekrWidget::class.java),
                    remoteViews
                )
            }

            CHECK_DAY -> {
                val todayName = DateManager.getTodayName()
                val savedDay = HawkManager.getZekrDay()
                if (todayName != savedDay) {
                    HawkManager.saveCustomZekr(customZekr = 0)
                    HawkManager.saveZekrDay(day = DateManager.getTodayName())
                    remoteViews.setTextViewText(
                        R.id.tvCustomZekrCounter,
                        HawkManager.getCustomZekr().toString()
                    )
                    AppWidgetManager.getInstance(context).updateAppWidget(
                        ComponentName(context, CustomZekrWidget::class.java),
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
        val views = RemoteViews(context.packageName, R.layout.widget_custom_zekr)
        HawkManager.saveZekrDay(day = DateManager.getTodayName())
        views.setTextViewText(R.id.tvCustomZekr, HawkManager.getCustomZekrTitle())
        views.setTextViewText(R.id.tvCustomZekrCounter, HawkManager.getCustomZekr().toString())
        views.setOnClickPendingIntent(
            R.id.tvCustomZekrCounter,
            updateCustomZekrIntent(
                context = context,
                action = CUSTOM_ZEKR,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tvCustomZekrNotice,
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
     * generate the specific pending intent to update custom zekr counter then return it.
     */
    private fun updateCustomZekrIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, CustomZekrWidget::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }

}