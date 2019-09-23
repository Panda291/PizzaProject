package com.example.pizzaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener{

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

//        PizzaDbHelper databaseHelper = new PizzaDbHelper(this);
//        final SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentResolver resolver = getContentResolver();


        String[] tableColumns = new String[] {
                "name",
                "id",
                "price"
        };
        String whereClause = null;
        String[] whereArgs = new String[] {
        };
        String orderBy = "id";
        Uri uri = Uri.parse("content://com.example.pizzaproject.PizzaContentProvider/pizza");
        Cursor databaseOutput = resolver.query(uri, tableColumns, whereClause, whereArgs, orderBy);

        databaseOutput.moveToFirst();
        Log.d("databaseoutput", databaseOutput.getString(1));

        recyclerAdapter = new RecyclerAdapter(this, databaseOutput, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("onListItemClick", String.valueOf(clickedItemIndex));
    }
}
