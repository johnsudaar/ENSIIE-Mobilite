package com.ensiie.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by john on 29/11/16.
 */

public class MainActivityE82 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_08_2);

        Intent intent = getIntent();
        String text = intent.getStringExtra("from81");

        if(text != null && text.length() != 0){
            TextView tv = (TextView)findViewById(R.id.text_from_activity_08_1);
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
        }

        Button btn = (Button)findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)findViewById(R.id.edit_text);
                Intent intent = new Intent();
                intent.putExtra("from82",et.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}