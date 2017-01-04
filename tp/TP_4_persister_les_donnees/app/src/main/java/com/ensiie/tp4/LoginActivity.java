package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final CheckBox cb           = (CheckBox) findViewById(R.id.memorise_user);
        final EditText username    = (EditText)  findViewById(R.id.login);
        final EditText password    = (EditText)  findViewById(R.id.password);
        final Button loginButton   = (Button)    findViewById(R.id.button_login);
        final Button newUserButton = (Button)    findViewById(R.id.button_new_user);


        // Shared Preferences
        SharedPreferences pref = getSharedPreferences("ensiie.login", MODE_PRIVATE);
        String login = pref.getString("login","");
        String pass  = pref.getString("password","");
        username.setText(login);
        password.setText(pass);
        cb.setChecked(pref.getBoolean("remember", false));

        // OnLogin
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("ensiie.login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                if(cb.isChecked()){
                    editor.putString("login", username.getText().toString());
                    editor.putString("password", username.getText().toString());
                    editor.putBoolean("remember", true);
                    editor.apply();
                } else {
                    editor.remove("login");
                    editor.remove("password");
                    editor.putBoolean("remember", false);
                    editor.apply();
                }

                StorageManager.getInstance().setUser(new User(username.getText().toString(), password.getText().toString()));

                Intent myIntent = new Intent(LoginActivity.this, HomeActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });


        // OnNewUser
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(LoginActivity.this, NewUserActivity.class);
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }
}
