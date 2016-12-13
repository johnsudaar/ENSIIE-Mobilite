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

public class Exercice1Activity extends AppCompatActivity {

    private static int TASK_FINISHED =1;
    private ProgressBar progressBar;
    private Button button;

    private Handler handler = new Handler();

    class UtilsRunnable implements Runnable {
        @Override
        public void run(){
            Utils.workToDo();
            handler.post(new Runnable(){
                @Override
                public void run() {
                    Button b = (Button) findViewById(R.id.button);
                    b.setVisibility(View.VISIBLE);
                }
            });
        }
    }

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
            Button b = (Button)findViewById(R.id.button);
            b.setVisibility(View.INVISIBLE);
            Thread thread =  new Thread(new UtilsRunnable());
            thread.start();
        }
    };
}