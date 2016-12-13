package com.ensiie.tp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Adrian on 29/11/2016.
 */
public class NewUserConfirmActivity extends Activity {
    private static final String EXTRA_USER = "extra_user";

    private User user;

    public static Intent newIntent(Context context, User user) {
        final Intent intent = new Intent(context, NewUserConfirmActivity.class);
        intent.putExtra(EXTRA_USER, user);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form_confirm);

        final TextView addressTV = (TextView) findViewById(R.id.address);
        final TextView countryTV = (TextView) findViewById(R.id.country);
        final TextView loginTV = (TextView) findViewById(R.id.login);

        if (savedInstanceState != null) {
            user = (User) savedInstanceState.getSerializable(EXTRA_USER);
        } else if (getIntent() != null && getIntent().getExtras() != null) {
            user = (User) getIntent().getSerializableExtra(EXTRA_USER);
        }

        if (user != null) {
            loginTV.setText(user.getLogin());
            addressTV.setText(user.getAddress());
            countryTV.setText(user.getCountry());
        } else {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(EXTRA_USER, user);
    }
}
