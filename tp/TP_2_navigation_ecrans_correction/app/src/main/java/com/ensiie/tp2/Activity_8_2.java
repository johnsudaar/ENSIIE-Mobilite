package com.ensiie.tp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_8_2 extends Activity {

    public static final String EXTRA_TEXT = "extra_text";

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_08_2);

        final TextView textView = (TextView) findViewById(R.id.text_from_activity_08_1);
        final String text = getIntent().getStringExtra(EXTRA_TEXT);
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }

        final Button buttonSetResult = (Button) findViewById(R.id.button);
        buttonSetResult.setOnClickListener(buttonSetResultListener);

        editText = (EditText) findViewById(R.id.edit_text);
    }

    private View.OnClickListener buttonSetResultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Intent intent = new Intent();
            intent.putExtra(EXTRA_TEXT, editText.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    };
}
