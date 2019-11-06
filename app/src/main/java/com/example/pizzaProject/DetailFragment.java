package com.example.pizzaProject;

import android.content.ContentResolver;
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

public class DetailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fr_detailview, container, false);

        ContentResolver resolver = getActivity().getContentResolver();

        String[] tableColumns = new String[]{
                "name",
                "description",
                "price"
        };
        String whereClause = null;
        String[] whereArgs;
        Uri uri;
        if (getActivity().findViewById(R.id.tablet) != null) {
            TextView temp = getActivity().findViewById(R.id.selectedId);
            whereArgs = new String[]{"id"};
            whereClause = "id = " + temp.getText().toString();
            Log.d("findId(tablet)", String.valueOf(temp.getText().toString()));
            uri = Uri.parse("content://com.example.pizzaProject/pizza/" + temp.getText().toString());
        } else {
            String temp = getActivity().getIntent().getStringExtra("EXTRA_ID");
            whereArgs = new String[]{"id"};
            whereClause = "id = " + temp;
            Log.d("findId(phone)", temp);
            uri = Uri.parse("content://com.example.pizzaProject/pizza/" + temp);
        }
        String orderBy = null;

        Cursor databaseOutput = resolver.query(uri, tableColumns, whereClause, whereArgs, orderBy);

        databaseOutput.moveToFirst();
        Log.d("DetailOutput", databaseOutput.getString(1));

        TextView productDescription = rootView.findViewById(R.id.productDescription);
        productDescription.setText(databaseOutput.getString(databaseOutput.getColumnIndex("description")));

        databaseOutput.close();

        return rootView;
    }
}
