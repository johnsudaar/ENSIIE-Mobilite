package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.ensiie.tp3.bo.TextDescription;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice9Activity extends AppCompatActivity {

    private ArrayList<TextDescription> dataSimpleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_recycler_view);

        dataSimpleList = DataGenerator.getDataForSimpleList();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final Exercice9Adapter adapter = new Exercice9Adapter(dataSimpleList);
        adapter.setListener(listener);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    Exercice9Adapter.MyItemClickListener listener = new Exercice9Adapter.MyItemClickListener() {
        @Override
        public void onItemClickListener(int position) {
            Utils.showMessage(Exercice9Activity.this, dataSimpleList.get(position).toString());
        }
    };

}
