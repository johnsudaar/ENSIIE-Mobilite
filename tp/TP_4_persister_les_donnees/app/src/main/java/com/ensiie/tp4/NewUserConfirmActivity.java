package com.ensiie.tp4;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
=======
import android.os.Bundle;
>>>>>>> master

public class NewUserConfirmActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form_confirm);
<<<<<<< HEAD

        User u = (User) getIntent().getSerializableExtra("user");

        TextView loginTV = (TextView) findViewById(R.id.login);
        TextView countTV = (TextView) findViewById(R.id.country);
        TextView addrTV  = (TextView) findViewById(R.id.address);

        getIntent().getExtras().clear();

        loginTV.setText(getString(R.string.text_login)+" : "+ u.getLogin());
        countTV.setText(getString(R.string.text_country)+" : "+ u.getCountry());
        addrTV.setText(getString(R.string.text_address)+" : "+ u.getAddress());
=======
>>>>>>> master
    }
}
