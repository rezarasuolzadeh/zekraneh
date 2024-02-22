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
import ir.rezarasoulzadeh.zekraneh.utils.Constants
import ir.rezarasoulzadeh.zekraneh.utils.Constants.TASBIHAT_AA
import ir.rezarasoulzadeh.zekraneh.utils.Constants.TASBIHAT_HA
import ir.rezarasoulzadeh.zekraneh.utils.Constants.TASBIHAT_SA
import ir.rezarasoulzadeh.zekraneh.utils.HawkManager

class TasbihatActivity : AppWidgetProvider() {

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
        if (TASBIHAT_AA == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasbihat)
            remoteViews.setTextViewText(
                R.id.AACounterTextView,
                HawkManager.increaseTasbihatAA().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java),
                remoteViews
            )
        }
        if (TASBIHAT_SA == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasbihat)
            remoteViews.setTextViewText(
                R.id.SACounterTextView,
                HawkManager.increaseTasbihatSA().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java),
                remoteViews
            )
        }
        if (TASBIHAT_HA == intent.action) {
            val remoteViews = RemoteViews(context.packageName, R.layout.widget_tasbihat)
            remoteViews.setTextViewText(
                R.id.HACounterTextView,
                HawkManager.increaseTasbihatHA().toString()
            )
            AppWidgetManager.getInstance(context).updateAppWidget(
                ComponentName(context, TasbihatActivity::class.java),
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
        val views = RemoteViews(context.packageName, R.layout.widget_tasbihat)
        views.setTextViewText(R.id.AACounterTextView, HawkManager.getTasbihatAA().toString())
        views.setTextViewText(R.id.SACounterTextView, HawkManager.getTasbihatSA().toString())
        views.setTextViewText(R.id.HACounterTextView, HawkManager.getTasbihatHA().toString())
        views.setOnClickPendingIntent(
            R.id.AACounterTextView,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_AA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.SACounterTextView,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_SA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.HACounterTextView,
            updateTasbihatIntent(
                context = context,
                action = TASBIHAT_HA,
                appWidgetId = appWidgetId
            )
        )
        views.setOnClickPendingIntent(
            R.id.tasbihatTitleText,
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
     * generate the specific pending intent to update tasbihat counter then return it.
     */
    private fun updateTasbihatIntent(
        context: Context?,
        action: String?,
        appWidgetId: Int
    ): PendingIntent? {
        val intent = Intent(context, TasbihatActivity::class.java)
        intent.action = action
        intent.putExtra("id", appWidgetId)
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

}