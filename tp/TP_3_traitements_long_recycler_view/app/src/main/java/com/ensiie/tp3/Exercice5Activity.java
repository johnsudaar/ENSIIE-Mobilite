package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Adrian on 19/11/2016.
 */
public class Exercice5Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button;
    private TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_task);

        percent = (TextView) findViewById(R.id.text);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonClickListener);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            for (int i = 1; i < 11; i++) {
                Utils.smallWorkToDo();
                percent.setText(i * 10);
            }
        }
    };
}

