package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Activity_6 extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_06);

        imageView = (ImageView) findViewById(R.id.image);

        final ImageButton imageButton1 = (ImageButton) findViewById(R.id.image_button_1);
        final ImageButton imageButton2 = (ImageButton) findViewById(R.id.image_button_2);
        final ImageButton imageButton3 = (ImageButton) findViewById(R.id.image_button_3);

        imageButton1.setImageResource(R.drawable.image_button_1);
        imageButton1.setTag(R.drawable.image_button_1);

        imageButton2.setImageResource(R.drawable.image_button_2);
        imageButton2.setTag(R.drawable.image_button_2);

        imageButton3.setImageResource(R.drawable.image_button_3);
        imageButton3.setTag(R.drawable.image_button_3);

        imageButton1.setOnClickListener(clickListener);
        imageButton2.setOnClickListener(clickListener);
        imageButton3.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            imageView.setImageResource((Integer) v.getTag());
        }
    };
}
