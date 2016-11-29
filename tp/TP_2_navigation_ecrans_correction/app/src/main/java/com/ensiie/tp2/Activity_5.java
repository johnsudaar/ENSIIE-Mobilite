package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_5 extends Activity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_05);

        title = (TextView) findViewById(R.id.title);

        final Button button1 = (Button) findViewById(R.id.button_1);
        final Button button2 = (Button) findViewById(R.id.button_2);
        final Button button3 = (Button) findViewById(R.id.button_3);

        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            title.setText(button.getText());
        }
    };
}
