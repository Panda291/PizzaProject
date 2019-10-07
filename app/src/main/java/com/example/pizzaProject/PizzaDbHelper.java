package com.example.pizzaProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PizzaDbHelper extends SQLiteOpenHelper {
    public PizzaDbHelper(Context context)
    {
        super(context, "Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pizza(id INTEGER PRIMARY KEY, name TEXT, price REAL)");

        ContentValues values = new ContentValues();
        values.put("name", "pizza margharita");
        values.put("price", 9.00);

        long newRowId = db.insert("pizza", null, values);
        Log.d("rowID", String.valueOf(newRowId));
        values = new ContentValues();

        values.put("name", "pizza mozzarella");
        values.put("price", 12.00);

        db.insert("pizza", null, values);
        values = new ContentValues();

        values.put("name", "test pizza");
        values.put("price", 999.00);

        db.insert("pizza", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
