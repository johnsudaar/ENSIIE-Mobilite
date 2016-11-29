package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

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
    protected void onStop(){
        super.onStop();
        pauseTimer();
    }

    @Override
    protected void onStart(){
        super.onResume();
        playTimer();
    }

    // region Timer

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

    //endregion
}
