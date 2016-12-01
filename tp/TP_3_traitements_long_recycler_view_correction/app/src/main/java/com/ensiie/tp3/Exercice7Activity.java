package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice7Activity extends AppCompatActivity {

    private ArrayList<String> dataSimpleGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);

        dataSimpleGrid = DataGenerator.getDataForSimpleGrid();

        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercice_07_item_grid, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final String counter = dataSimpleGrid.get(position);
            holder.counter.setText(counter);
        }

        @Override
        public int getItemCount() {
            return dataSimpleGrid.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView counter;

        public MyViewHolder(View itemView) {
            super(itemView);
            counter = (TextView) itemView.findViewById(R.id.counter);
        }
    }
}
