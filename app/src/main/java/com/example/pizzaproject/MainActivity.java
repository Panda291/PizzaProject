package com.example.pizzaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.ListItemClickListener{

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        PizzaDbHelper databaseHelper = new PizzaDbHelper(this);
        final SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] tableColumns = new String[] {
                "name",
                "id",
                "price"
        };
        String whereClause = "";
        String[] whereArgs = new String[] {
        };
        String orderBy = "id";
        Cursor databaseOutput = db.query("pizza", tableColumns, whereClause, whereArgs, null, null, orderBy);

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
