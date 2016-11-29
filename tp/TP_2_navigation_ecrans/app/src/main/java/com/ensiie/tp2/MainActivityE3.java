package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by john on 29/11/16.
 */

public class MainActivityE3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_03);

        TextView title = (TextView) findViewById(R.id.title);
        String titleText = String.format(title.getText().toString(), "john");
        title.setText(titleText);

        TextView description = (TextView) findViewById(R.id.description);
        String descriptionText = String.format(description.getText().toString(), 3, 10);
        description.setText(descriptionText);

    }
}
