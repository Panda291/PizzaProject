package com.example.pizzaProject;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaProject.DetailActivity;

public class OverviewFragment extends Fragment implements RecyclerAdapter.ListItemClickListener {

    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fr_overview, container);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

//        PizzaDbHelper databaseHelper = new PizzaDbHelper(this);
//        final SQLiteDatabase db = databaseHelper.getReadableDatabase();
        ContentResolver resolver = getActivity().getContentResolver();


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

        databaseOutput.moveToFirst();
        Log.d("databaseoutput", databaseOutput.getString(1));

        recyclerAdapter = new RecyclerAdapter(databaseOutput, this);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Log.d("onListItemClick", String.valueOf(clickedItemIndex));
        clickedItemIndex++;
        Log.d("onListItemClick", String.valueOf(clickedItemIndex));


        if(getActivity().findViewById(R.id.tablet) != null)
        {
            TextView selectedId = getActivity().findViewById(R.id.selectedId);
            selectedId.setText(String.valueOf(clickedItemIndex));
            Log.d("onlistitemclick", selectedId.getText().toString());

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            DetailFragment detailFragment = new DetailFragment();

            fragmentManager.beginTransaction()
                    .replace(R.id.fr_detail, detailFragment)
                    .commit();
        }
        else
        {
            Intent myIntent = new Intent(getActivity(), DetailActivity.class);
            myIntent.putExtra("EXTRA_ID", String.valueOf(clickedItemIndex));
            startActivity(myIntent);
        }

    }
}

