package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.content.res.AppCompatResources;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by john on 29/11/16.
 */

public class MainActivityE6 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_06);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton btn = (ImageButton) view;

                int id = (Integer)btn.getTag();

                ImageView iv = (ImageView) findViewById(R.id.image);
                iv.setImageDrawable(AppCompatResources.getDrawable(MainActivityE6.this, id));

            }
        };

        ImageButton ib1 = (ImageButton) findViewById(R.id.image_button_1);
        ib1.setTag(R.drawable.image_button_1);
        ib1.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.image_button_1));
        ib1.setOnClickListener(listener);

        ImageButton ib2 = (ImageButton) findViewById(R.id.image_button_2);
        ib2.setTag(R.drawable.image_button_2);
        ib2.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.image_button_2));
        ib2.setOnClickListener(listener);

        ImageButton ib3 = (ImageButton) findViewById(R.id.image_button_3);
        ib3.setTag(R.drawable.image_button_3);
        ib3.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.image_button_3));
        ib3.setOnClickListener(listener);


    }
}
