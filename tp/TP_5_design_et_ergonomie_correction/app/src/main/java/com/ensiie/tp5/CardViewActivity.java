package com.ensiie.tp5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class CardViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
        setTitle(getString(R.string.exercise_card));

        final Button secondary = (Button) findViewById(R.id.action_secondary);
        secondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardViewActivity.this, getString(R.string.cardview_toast_action_secondary), LENGTH_SHORT).show();
            }
        });

        final Button main = (Button) findViewById(R.id.action_main);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CardViewActivity.this, getString(R.string.cardview_toast_action_main), LENGTH_SHORT).show();
            }
        });
    }
}
