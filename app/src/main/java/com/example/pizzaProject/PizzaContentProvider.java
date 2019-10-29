package com.example.pizzaProject;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PizzaContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.pizzaProject";
    public static final String PIZZA_PATH = "pizza";
    public static final int ALL_PIZZAS = 100;
    public static final int PIZZA_ID = 101;
    public static UriMatcher uriMatcher = buildUriMatcher();

    PizzaDbHelper mPizzaDbHelper;
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        mPizzaDbHelper = new PizzaDbHelper(context);
        db = mPizzaDbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d("ContentProviderQuery", "main");
        int match = uriMatcher.match(uri);
        Cursor databaseOutput;
        switch (match) {
            case ALL_PIZZAS:
                Log.d("ContentProviderQuery", "ALL_PIZZAS");
                databaseOutput = db.query("pizza", projection, selection, selectionArgs, null, null, sortOrder);
                return databaseOutput;
            case PIZZA_ID:
                Log.d("ContentProviderQuery", "PIZZA_ID");

                SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
                qb.setTables("pizza");
                String sql = qb.buildQuery(projection, selection, null, null, sortOrder, null);
                Log.d("Example", sql);

                Log.d("Example", selection);
                databaseOutput = db.query("pizza", projection, selection, null, null, null, sortOrder);
                return databaseOutput;
            default:
                Log.d("ContentProviderQuery", "default");
                return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public static UriMatcher buildUriMatcher() {
        Log.d("UriMatcher", "main");
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PIZZA_PATH, ALL_PIZZAS);
        uriMatcher.addURI(AUTHORITY, PIZZA_PATH + "/#", PIZZA_ID);

        return uriMatcher;
    }
}
