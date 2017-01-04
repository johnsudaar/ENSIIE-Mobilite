package com.ensiie.tp5.internal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ensiie.tp5.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(getString(R.string.exercise_fab), 1));
        exercises.add(new Exercise(getString(R.string.exercise_card), 2));
        exercises.add(new Exercise(getString(R.string.exercise_menu), 3));
        exercises.add(new Exercise(getString(R.string.exercise_toolbar), 4));
        exercises.add(new Exercise(getString(R.string.exercise_spinner), 5));
        exercises.add(new Exercise(getString(R.string.exercise_tabs), 6));
        exercises.add(new Exercise(getString(R.string.exercise_collapsible), 7));
        exercises.add(new Exercise(getString(R.string.exercise_collapsible_parallax), 8));
        exercises.add(new Exercise(getString(R.string.exercise_collapsible_fab), 9));

        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Exercise exercise = (Exercise) parent.getAdapter().getItem(position);
        switch (exercise.getNumber()) {
            case 1:
                startActivity(new Intent(MainActivity.this, FabActivity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, CardViewActivity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, IconsActivity.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, AppBarActivity.class));
                break;
            case 5:
                startActivity(new Intent(MainActivity.this, AppBarSpinnerActivity.class));
                break;
            case 6:
                startActivity(new Intent(MainActivity.this, AppBarTabsActivity.class));
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, AppBarCollapsibleActivity.class));
                break;
            case 8:
                startActivity(new Intent(MainActivity.this, AppBarCollapsibleParallaxActivity.class));
                break;
            case 9:
                startActivity(new Intent(MainActivity.this, AppBarCollapsibleFabActivity.class));
                break;
        }

    }
}
