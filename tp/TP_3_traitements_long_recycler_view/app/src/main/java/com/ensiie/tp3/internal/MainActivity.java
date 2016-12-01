package com.ensiie.tp3.internal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ensiie.tp3.*;
import com.ensiie.tp3.bo.Exercise;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise(getString(R.string.exercise_1), 1));
        exercises.add(new Exercise(getString(R.string.exercise_2), 2));
        exercises.add(new Exercise(getString(R.string.exercise_3), 3));
        exercises.add(new Exercise(getString(R.string.exercise_4), 4));
        exercises.add(new Exercise(getString(R.string.exercise_5), 5));
        exercises.add(new Exercise(getString(R.string.exercise_6), 6));
        exercises.add(new Exercise(getString(R.string.exercise_7), 7));
        exercises.add(new Exercise(getString(R.string.exercise_8), 8));
        exercises.add(new Exercise(getString(R.string.exercise_9), 9));
        exercises.add(new Exercise(getString(R.string.exercise_10), 10));
        exercises.add(new Exercise(getString(R.string.exercise_11), 11));
        exercises.add(new Exercise(getString(R.string.exercise_12), 12));
        exercises.add(new Exercise(getString(R.string.exercise_13), 13));

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
                startActivity(new Intent(MainActivity.this, Exercice1Activity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Exercice2Activity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Exercice3Activity.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, Exercice4Activity.class));
                break;
            case 5:
                startActivity(new Intent(MainActivity.this, Exercice5Activity.class));
                break;
            case 6:
                startActivity(new Intent(MainActivity.this, Exercice6Activity.class));
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, Exercice7Activity.class));
                break;
            case 8:
                startActivity(new Intent(MainActivity.this, Exercice8Activity.class));
                break;
            case 9:
                startActivity(new Intent(MainActivity.this, Exercice9Activity.class));
                break;
            case 10:
                startActivity(new Intent(MainActivity.this, Exercice10Activity.class));
                break;
            case 11:
                startActivity(new Intent(MainActivity.this, Exercice11Activity.class));
                break;
            case 12:
                startActivity(new Intent(MainActivity.this, Exercice12Activity.class));
                break;
            case 13:
                startActivity(new Intent(MainActivity.this, Exercice13_1Activity.class));
                break;
        }

    }
}
