package com.ensiie.tp3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by Adrian on 19/11/2016.
 */
public class Exercice2Activity extends AppCompatActivity {
    private static final int START = 0;
    private static final int DONE = 1;

    private ProgressBar progressBar;
    private Button button;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == START) {
                button.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_task);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonClickListener);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Thread thread = new Thread() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(START);
                    Utils.workTodo();
                    handler.sendEmptyMessage(DONE);
                }
            };
            thread.start();
        }
    };
}

