package com.example.pizzaProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.tablet) != null)
        {
            Log.d("Main/OnCreate", "tablet");
        }

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("FAB", "clicked");
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                String temp = data.toString();

                Log.d("MainActivity", temp);

                ContentResolver resolver = MainActivity.this.getContentResolver();

                Pattern p = Pattern.compile("Intent \\{ dat=content:\\/\\/com\\.example\\.pizzaProject\\/pizza\\/(\\d+)");
                Matcher m = p.matcher(temp);
                if (m.find())
                {
                    MatchResult mr = m.toMatchResult();
                    Log.d("MainActivity", mr.group(1));

                    if(Integer.parseInt(mr.group(1)) == -1)
                    {
                        Snackbar.make(MainActivity.this.findViewById(R.id.fr_overview), "An unknown error has occurred (fake)", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                    }
                    else
                    {

                        Snackbar.make(MainActivity.this.findViewById(R.id.fr_overview), "pizza added at id: " + mr.group(1), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        recreate();
                    }
                }

            }
        }
    }
}
