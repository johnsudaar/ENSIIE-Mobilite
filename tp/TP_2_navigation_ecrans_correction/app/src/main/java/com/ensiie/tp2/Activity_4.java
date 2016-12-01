package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity_4 extends Activity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_04);

        title = (TextView) findViewById(R.id.title);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // To avoid deprecation use : title.setTextColor(ContextCompat.getColor(Activity_1.this, R.color.red));
            title.setTextColor(getResources().getColor(R.color.red));
        }
    };
}
