package com.ensiie.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Adrian on 29/11/2016.
 */
public class NewUserActivity extends Activity {

    private EditText login;
    private EditText country;
    private EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(clickListener);

        login = (EditText) findViewById(R.id.login);
        country = (EditText) findViewById(R.id.country);
        address = (EditText) findViewById(R.id.address);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(NewUserConfirmActivity.newIntent(NewUserActivity.this, new User(login.getText().toString(),
                    country.getText().toString(), address.getText().toString())));
        }
    };
}
