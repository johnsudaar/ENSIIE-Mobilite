package com.ensiie.tp1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.ensiie.tp1.exercises.*;
import com.ensiie.tp1.model.Exercise;

import java.util.ArrayList;

public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

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
        exercises.add(new Exercise(getString(R.string.exercise_14), 14));
        exercises.add(new Exercise(getString(R.string.exercise_15), 15));
        exercises.add(new Exercise(getString(R.string.exercise_16), 16));
        exercises.add(new Exercise(getString(R.string.exercise_17), 17));
        exercises.add(new Exercise(getString(R.string.exercise_18), 18));
        exercises.add(new Exercise(getString(R.string.exercise_19), 19));
        exercises.add(new Exercise(getString(R.string.exercise_20), 20));
        exercises.add(new Exercise(getString(R.string.exercise_21), 21));
        exercises.add(new Exercise(getString(R.string.exercise_22), 22));

        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exercises);
        setListAdapter(listAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final Exercise exercise = (Exercise) parent.getAdapter().getItem(position);
        switch (exercise.getNumber()) {
            case 1:
                startActivity(new Intent(MainActivity.this, Exercise1Activity.class));
                break;
            case 2:
                startActivity(new Intent(MainActivity.this, Exercise2Activity.class));
                break;
            case 3:
                startActivity(new Intent(MainActivity.this, Exercise3Activity.class));
                break;
            case 4:
                startActivity(new Intent(MainActivity.this, Exercise4Activity.class));
                break;
            case 5:
                startActivity(new Intent(MainActivity.this, Exercise5Activity.class));
                break;
            case 6:
                startActivity(new Intent(MainActivity.this, Exercise6Activity.class));
                break;
            case 7:
                startActivity(new Intent(MainActivity.this, Exercise7Activity.class));
                break;
            case 8:
                startActivity(new Intent(MainActivity.this, Exercise8Activity.class));
                break;
            case 9:
                startActivity(new Intent(MainActivity.this, Exercise9Activity.class));
                break;
            case 10:
                startActivity(new Intent(MainActivity.this, Exercise10Activity.class));
                break;
            case 11:
                startActivity(new Intent(MainActivity.this, Exercise11Activity.class));
                break;
            case 12:
                startActivity(new Intent(MainActivity.this, Exercise12Activity.class));
                break;
            case 13:
                startActivity(new Intent(MainActivity.this, Exercise13Activity.class));
                break;
            case 14:
                startActivity(new Intent(MainActivity.this, Exercise14Activity.class));
                break;
            case 15:
                startActivity(new Intent(MainActivity.this, Exercise15Activity.class));
                break;
            case 16:
                startActivity(new Intent(MainActivity.this, Exercise16Activity.class));
                break;
            case 17:
                startActivity(new Intent(MainActivity.this, Exercise17Activity.class));
                break;
            case 18:
                startActivity(new Intent(MainActivity.this, Exercise18Activity.class));
                break;
            case 19:
                startActivity(new Intent(MainActivity.this, Exercise19Activity.class));
                break;
            case 20:
                startActivity(new Intent(MainActivity.this, Exercise20Activity.class));
                break;
            case 21:
                startActivity(new Intent(MainActivity.this, Exercise21Activity.class));
                break;
            case 22:
                startActivity(new Intent(MainActivity.this, Exercise22Activity.class));
                break;
        }

    }
}
