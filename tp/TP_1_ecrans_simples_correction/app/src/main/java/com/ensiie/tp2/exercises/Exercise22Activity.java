package com.ensiie.tp2.exercises;

import android.app.Activity;
import android.os.Bundle;
import com.ensiie.tp2.R;

/**
 * Created by Adrian on 11/09/16.
 */
public class Exercise22Activity extends Activity {
    private static final String TAG = "com.ensiie.tp2.Exercice1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_22);
        setTitle(getString(R.string.exercise_22));
    }
}
