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

public class MainActivityE8 extends Activity{
    static final int REQUEST_TEXT = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_08_1);

        Button bNoData = (Button)findViewById(R.id.button_no_data);

        bNoData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(MainActivityE8.this, MainActivityE82.class);
                MainActivityE8.this.startActivity(activityIntent);
            }
        });

        Button btnData = (Button)findViewById(R.id.button_data);

        btnData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(MainActivityE8.this, MainActivityE82.class);
                EditText te = (EditText) findViewById(R.id.text_to_send_to_activity_08_2);

                activityIntent.putExtra("from81", te.getText().toString());
                startActivity(activityIntent);
            }
        });

        Button btnResult = (Button) findViewById(R.id.button_result);

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activityIntent = new Intent(MainActivityE8.this, MainActivityE82.class);
                startActivityForResult(activityIntent, REQUEST_TEXT);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_TEXT && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            String result =  bundle.getString("from82");
            TextView tv = (TextView)findViewById(R.id.text_from_activity_08_2);
            tv.setText(result);
            tv.setVisibility(View.VISIBLE);
        }
    }
}
