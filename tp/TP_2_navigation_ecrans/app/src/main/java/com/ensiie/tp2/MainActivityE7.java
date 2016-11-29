package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ensiie.tp2.R;

public class MainActivityE7 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_07);

        CheckBox cb = (CheckBox)findViewById(R.id.checkbox);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox cb = (CheckBox) view;
                TextView desc = (TextView)findViewById(R.id.description);
                TextView title = (TextView)findViewById(R.id.title);
                if(cb.isChecked()){
                    desc.setVisibility(View.VISIBLE);
                    title.setVisibility(View.INVISIBLE);
                } else {
                    title.setVisibility(View.VISIBLE);
                    desc.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}