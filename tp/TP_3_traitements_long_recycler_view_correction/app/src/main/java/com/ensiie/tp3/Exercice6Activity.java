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

    private ArrayList<TextDescription> dataSimpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);

        dataSimpleList = DataGenerator.getDataForSimpleList();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercice_06_item_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            final TextDescription textDescription = dataSimpleList.get(position);
            holder.title.setText(textDescription.getText());
            holder.description.setText(textDescription.getDescription());
        }

        @Override
        public int getItemCount() {
            return dataSimpleList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }
}
