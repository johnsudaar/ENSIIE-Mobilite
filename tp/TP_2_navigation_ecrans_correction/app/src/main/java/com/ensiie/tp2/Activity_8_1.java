package com.ensiie.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_8_1 extends Activity {

    public static final int RC_ACTIVITY_8_2 = 1;
    private EditText textToSendToActivity;
    private TextView textFromActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_08_1);

        textToSendToActivity = (EditText) findViewById(R.id.text_to_send_to_activity_08_2);
        textFromActivity = (TextView) findViewById(R.id.text_from_activity_08_2);

        final Button buttonNoData = (Button) findViewById(R.id.button_no_data);
        buttonNoData.setOnClickListener(buttonNoDataClickListener);

        final Button buttonData = (Button) findViewById(R.id.button_data);
        buttonData.setOnClickListener(buttonClickListener);

        final Button buttonWaitResult = (Button) findViewById(R.id.button_result);
        buttonWaitResult.setOnClickListener(buttonWaitResultClickListener);
    }

    private View.OnClickListener buttonNoDataClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(Activity_8_1.this, Activity_8_2.class);
            startActivity(intent);
        }
    };

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(Activity_8_1.this, Activity_8_2.class);
            intent.putExtra(Activity_8_2.EXTRA_TEXT, textToSendToActivity.getText().toString());
            startActivity(intent);
        }
    };

    private View.OnClickListener buttonWaitResultClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(Activity_8_1.this, Activity_8_2.class);
            startActivityForResult(intent, RC_ACTIVITY_8_2);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_ACTIVITY_8_2 && resultCode == Activity.RESULT_OK) {
            final String text = data.getStringExtra(Activity_8_2.EXTRA_TEXT);
            if (!TextUtils.isEmpty(text)) {
                textFromActivity.setText(text);
                textFromActivity.setVisibility(View.VISIBLE);
            }
        }
    }
}
