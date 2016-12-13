package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ensiie.tp3.bo.TextDescription;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ViewHolderSimpleListAdapter adapter = new ViewHolderSimpleListAdapter(DataGenerator.getDataForSimpleList());
        recyclerView.setAdapter(adapter);
    }
}

class ViewHolderSimpleListAdapter extends RecyclerView.Adapter<SimpleItemListViewHolder> {
    private ArrayList<TextDescription> data;

    public ViewHolderSimpleListAdapter(ArrayList<TextDescription> data){
        this.data = data;
    }
    @Override
    public SimpleItemListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercice_06_item_list, parent, false);
        return new SimpleItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleItemListViewHolder holder, int position) {
        holder.setData(this.data.get(position));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }
}

class SimpleItemListViewHolder extends RecyclerView.ViewHolder{

    public SimpleItemListViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(TextDescription td){
        TextView title = (TextView) itemView.findViewById(R.id.title);
        TextView descr = (TextView) itemView.findViewById(R.id.description);

        title.setText(td.getText());
        descr.setText(td.getDescription());
    }
}