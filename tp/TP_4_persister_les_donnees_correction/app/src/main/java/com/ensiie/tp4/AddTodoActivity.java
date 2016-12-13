package com.ensiie.tp4;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensiie.tp4.database.DatabaseHelper;
import com.ensiie.tp4.database.Todo;

public class AddTodoActivity extends Activity {

    private DatabaseHelper databaseHelper;
    private ViewGroup loading;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        editText = (EditText) findViewById(R.id.text);
        loading = (ViewGroup) findViewById(R.id.loading);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(clickListener);

        databaseHelper = new DatabaseHelper(this);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddTask addTask = new AddTask();
            addTask.execute(new Todo(editText.getText().toString()));
        }
    };

    class AddTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected void onPreExecute() {
            loading.setVisibility(View.VISIBLE);
            button.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Todo[] params) {
            databaseHelper.addTodo(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final Intent intent = new Intent(AddTodoActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Supprime la backstack et lance HomeActivity
            startActivity(intent);
        }
    }
}
