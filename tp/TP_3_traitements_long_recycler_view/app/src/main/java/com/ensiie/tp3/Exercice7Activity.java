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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);

        GridItemAdapter adapter = new GridItemAdapter(DataGenerator.getDataForSimpleGrid());
        recyclerView.setAdapter(adapter);
    }
}

class GridItemAdapter extends RecyclerView.Adapter<GridViewHolder>{

    private ArrayList<String> data;

    public GridItemAdapter(ArrayList<String> data){
        this.data = data;
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_07_item_grid,parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        holder.setData(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

class GridViewHolder extends RecyclerView.ViewHolder{

    public GridViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(String str){
        TextView tv = (TextView)itemView.findViewById(R.id.counter);
        tv.setText(str);
    }
}
