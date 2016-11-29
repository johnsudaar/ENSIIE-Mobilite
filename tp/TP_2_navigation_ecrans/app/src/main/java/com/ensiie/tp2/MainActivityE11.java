package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ensiie.tp2.helpers.Utils;

/**
 * Created by john on 29/11/16.
 */

public class MainActivityE11 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_11);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.vibratePhone(MainActivityE11.this);
            }
        });
    }
}
