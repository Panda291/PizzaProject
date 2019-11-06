package com.example.pizzaProject;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 */
public class PizzaWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.pizza_widget);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

        ContentResolver resolver = context.getContentResolver();

        String[] tableColumns = new String[]{
                "name",
                "id",
                "price"
        };
        String whereClause = null;
        String[] whereArgs = new String[]{
        };
        String orderBy = "id";
        Uri uri = Uri.parse("content://com.example.pizzaProject/pizza");
        Cursor databaseOutput = resolver.query(uri, tableColumns, whereClause, whereArgs, orderBy);

        databaseOutput.moveToLast();

        views.setTextViewText(R.id.pizzaWidgetName, databaseOutput.getString(databaseOutput.getColumnIndex("name")));
        views.setTextViewText(R.id.pizzaWidgetPrice, "€" + String.valueOf(databaseOutput.getFloat(databaseOutput.getColumnIndex("price"))));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

