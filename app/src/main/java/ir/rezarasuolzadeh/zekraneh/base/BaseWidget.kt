package ir.rezarasuolzadeh.zekraneh.base

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle

abstract class BaseWidget : AppWidgetProvider() {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     overrides                                              //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        onAfterUpdate(
            context = context,
            appWidgetManager = appWidgetManager,
            appWidgetIds = appWidgetIds
        )
    }

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
        context?.let {
            appWidgetManager?.let {
                onAfterOptionChanged(
                    context = context,
                    appWidgetManager = appWidgetManager,
                    appWidgetId = appWidgetId
                )
            }
        }
    }

    override fun onReceive(
        context: Context,
        intent: Intent
    ) {
        super.onReceive(context, intent)
        onAfterReceive(
            context = context,
            intent = intent
        )
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     helpers                                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * after widget update, this function will be run.
     */
    protected open fun onAfterUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
    }

    /**
     * after every time we receive an intent in widget, this function will be run.
     */
    protected open fun onAfterReceive(
        context: Context,
        intent: Intent
    ) {
    }

    /**
     * after widget option has been changed, this function will be run.
     */
    protected open fun onAfterOptionChanged(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
    }

}