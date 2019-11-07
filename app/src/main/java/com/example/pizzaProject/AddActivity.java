package com.example.pizzaProject;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        Button addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AddActivity", "onClick addButton");
                EditText name = findViewById(R.id.NameEdit);
                EditText desc = findViewById(R.id.DescEdit);
                EditText price = findViewById(R.id.PriceEdit);

                if (!name.getText().toString().matches("") && !desc.getText().toString().matches("") && !price.getText().toString().matches(""))
                {
                    Log.d("AddActivity", "fields valid");
                    Log.d("AddActivity", name.getText().toString());
                    Log.d("AddActivity", desc.getText().toString());
                    Log.d("AddActivity", price.getText().toString());

                    ContentResolver resolver = AddActivity.this.getContentResolver();

                    ContentValues values = new ContentValues();

                    values.put("name", name.getText().toString());
                    values.put("description", desc.getText().toString());
                    values.put("price", String.valueOf(price.getText().toString()));

                    Uri uri = Uri.parse("content://com.example.pizzaProject/pizza/add");

                    Uri output = resolver.insert(uri, values);

                    Log.d("AddActivity Uri", output.toString());

                    Intent data = new Intent();

                    data.setData(output);
                    setResult(RESULT_OK, data);
                    finish();

                }
                else
                {
                    Log.d("AddActivity", "fields invalid");
                }
            }
        });
    }

}
