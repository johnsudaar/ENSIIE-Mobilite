package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        final Button confirmButton = (Button) findViewById(R.id.button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name    = (EditText) findViewById(R.id.login);
                EditText addr    = (EditText) findViewById(R.id.address);
                EditText country = (EditText) findViewById(R.id.country);

                User u = new User(name.getText().toString(), addr.getText().toString(), country.getText().toString());

                Intent myIntent = new Intent(NewUserActivity.this, NewUserConfirmActivity.class);

                myIntent.putExtra("user", u);

                NewUserActivity.this.startActivity(myIntent);
            }
        });
    }
}
