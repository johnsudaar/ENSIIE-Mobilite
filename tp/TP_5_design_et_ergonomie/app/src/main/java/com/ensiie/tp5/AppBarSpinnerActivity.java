package com.ensiie.tp5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AppBarSpinnerActivity extends AppCompatActivity {

    OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener(){
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {}
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) parent.getAdapter().getItem(position);
            TextView tv = (TextView) findViewById(R.id.text);
            tv.setText(selectedItem);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar_spinner);

        ArrayList<String> filters = new ArrayList<>();
        filters.add("Filter 1");
        filters.add("Filter 2");
        filters.add("Filter 3");

        Spinner spinner = (Spinner) findViewById(R.id.toolbar_spinner);

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_title, filters);
        adapter.setDropDownViewResource(R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
    }
}
