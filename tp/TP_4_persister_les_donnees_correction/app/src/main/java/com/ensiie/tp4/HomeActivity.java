package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Adrian on 29/11/2016.
 */
public class HomeActivity extends Activity {

    private UserSingleton userSingleton = UserSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final Button buttonAdd = (Button) findViewById(R.id.button_add_todo);
        final Button buttonSee = (Button) findViewById(R.id.button_see_todo);
        buttonAdd.setOnClickListener(clickAddListener);
        buttonSee.setOnClickListener(clickSeeListener);

        final TextView login = (TextView) findViewById(R.id.login);
        if (userSingleton.getUser() != null) {
            login.setText(String.format(getString(R.string.text_welcome), userSingleton.getUser().getLogin()));
        } else {
            logout();
        }
    }

    View.OnClickListener clickAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this, AddTodoActivity.class));
        }
    };

    View.OnClickListener clickSeeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this, TodoListActivity.class));
        }
    };

    private void logout() {
        final Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Supprime la backstack et lance LoginActivity
        startActivity(intent);
    }
}
