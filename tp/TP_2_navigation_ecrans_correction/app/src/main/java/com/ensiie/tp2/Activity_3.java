package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_03);

        final TextView titleTV = (TextView) findViewById(R.id.title);
        final TextView descriptionTV = (TextView) findViewById(R.id.description);

        final String text = String.format(getString(R.string.exercice_3_title), "Adrian");
        final String description = String.format(getString(R.string.exercice_3_description), 2, 3);

        titleTV.setText(text);
        descriptionTV.setText(description);
    }
}
