package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by john on 29/11/16.
 */

public class MainActivityE5 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_05);

        LinearLayout layout = (LinearLayout)findViewById(R.id.root);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.title);
                Button b = (Button) view;
                tv.setText(b.getText());

            }
        };
        for(int i = 0; i < layout.getChildCount(); i++){
            if(layout.getChildAt(i).getClass() == Button.class){
                Button b = (Button)layout.getChildAt(i);
                b.setOnClickListener(listener);
            }
        }
    }
}
