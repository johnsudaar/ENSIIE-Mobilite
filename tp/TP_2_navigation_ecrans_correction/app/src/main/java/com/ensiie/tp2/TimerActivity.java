package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Adrian on 19/11/2016.
 */
public class TimerActivity extends Activity {
    private CountDownTimer countDownTimer;
    private long millisLeft = 100000;
    private TextView timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        timer = (TextView) findViewById(R.id.timer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        playTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseTimer();
    }

    private void playTimer() {
        countDownTimer = new CountDownTimer(millisLeft, 1000) {

            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;
                timer.setText(millisUntilFinished / 1000 + "");
            }

            public void onFinish() {
                timer.setText("fin");
            }
        }.start();
    }

    private void pauseTimer() {
        countDownTimer.cancel();
    }
}
