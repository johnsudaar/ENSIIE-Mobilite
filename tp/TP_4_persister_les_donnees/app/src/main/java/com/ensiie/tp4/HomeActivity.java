package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User user = StorageManager.getInstance().getUser();
        if (user == null) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            this.startActivity(myIntent);
        } else {
            TextView username = (TextView) findViewById(R.id.login);
            username.setText(String.format(getString(R.string.text_welcome), user.getLogin()));
        }

        Button addTodo = (Button) findViewById(R.id.button_add_todo);
        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, AddTodoActivity.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });

        Button seeTodo = (Button) findViewById(R.id.button_see_todo);
        seeTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeActivity.this, TodoListActivity.class);
                HomeActivity.this.startActivity(myIntent);
            }
        });
    }
}
