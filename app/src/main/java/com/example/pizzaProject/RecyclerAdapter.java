package com.example.pizzaProject;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Cursor databaseOutput;
    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RecyclerAdapter(Cursor database, ListItemClickListener listener) {
        Log.d("RecyclerAdapter", "onCreate");
        databaseOutput = database;
        mOnClickListener = listener;
    }

    public void UpdateRecyclerAdapter(Cursor database)
    {
        databaseOutput = database;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("RecyclerAdapter", "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Log.d("onBind", String.valueOf(position));
        databaseOutput.moveToPosition(position);
        holder.descView.setText(databaseOutput.getString(databaseOutput.getColumnIndex("name")));
        holder.priceView.setText("€ " + String.valueOf(databaseOutput.getFloat(databaseOutput.getColumnIndex("price"))));
        holder.IdView.setText(String.valueOf(databaseOutput.getInt(databaseOutput.getColumnIndex("id"))));
    }

    @Override
    public int getItemCount() {
        return databaseOutput.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView descView;
        TextView priceView;
        TextView IdView;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            descView = (TextView) itemView.findViewById(R.id.descView);
            priceView = (TextView) itemView.findViewById(R.id.priceView);
            IdView = (TextView) itemView.findViewById(R.id.IdView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
