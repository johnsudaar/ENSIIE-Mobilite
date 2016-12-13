package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Adrian on 29/11/2016.
 */
public class LoginActivity extends Activity {

    private static final String PREFERENCE_LOGIN = "preference_login";
    private static final String PREFERENCE_PASSWORD = "preference_password";

    private EditText loginTV;
    private EditText password;
    private CheckBox checkBox;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(clickLoginListener);

        final Button buttonNewUser = (Button) findViewById(R.id.button_new);
        buttonNewUser.setOnClickListener(clickNewUserListener);

        loginTV = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.memorise_user);

        preferences = getSharedPreferences("ensiie", MODE_PRIVATE);

        loginTV.setText(preferences.getString(PREFERENCE_LOGIN, ""));
        password.setText(preferences.getString(PREFERENCE_PASSWORD, ""));
    }

    View.OnClickListener clickNewUserListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this, NewUserActivity.class));
        }
    };

    View.OnClickListener clickLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            saveUserInCache();
            saveUserInPreferences(checkBox.isChecked());

            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }
    };

    private void saveUserInCache() {
        final UserSingleton userSingleton = UserSingleton.getInstance();
        userSingleton.setUser(new User(loginTV.getText().toString(), password.getText().toString()));
    }

    private void saveUserInPreferences(boolean shouldMemorize) {
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREFERENCE_LOGIN, shouldMemorize ? loginTV.getText().toString() : "");
        editor.putString(PREFERENCE_PASSWORD, shouldMemorize ? password.getText().toString() : "");
        editor.apply();
    }
}
