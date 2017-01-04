package com.ensiie.tp5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AppBarSpinnerActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_spinner);

        ArrayList<String> filters = new ArrayList<>();
        filters.add("Filtre 1");
        filters.add("Filtre 2");
        filters.add("Filtre 3");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            // Permet de cacher le titre de l'App bar
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        textView = (TextView) findViewById(R.id.text);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.item_title_spinner, filters);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);
    }

    private AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) parent.getAdapter().getItem(position);
            textView.setText(selectedItem);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };
}
