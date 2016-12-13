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

    private static int MESSAGE_START = 1;
    private static int MESSAGE_END=2;

    private ProgressBar progressBar;
    private Button button;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message m){
            Button b = (Button) findViewById(R.id.button);
            ProgressBar pb = (ProgressBar) findViewById(R.id.progress_bar);
            if(m.what == MESSAGE_START){
                b.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.INVISIBLE);
            } else if (m.what == MESSAGE_END){
                b.setVisibility(View.VISIBLE);
                pb.setVisibility(View.VISIBLE);
            }
        }
    };

    class UtilsRunnable implements Runnable {

        @Override
        public void run() {
            handler.sendEmptyMessage(MESSAGE_START);
            Utils.workToDo();
            handler.sendEmptyMessage(MESSAGE_END);
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
            Thread thread = new Thread(new UtilsRunnable());
            thread.start();
        }
    };
}

