package com.ensiie.tp4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.ensiie.tp4.database.DatabaseHelper;
import com.ensiie.tp4.database.Todo;

public class EditTodoActivity extends Activity {

    public static final String EXTRA_TODO = "extra_todo";

    private DatabaseHelper databaseHelper;
    private ViewGroup loading;
    private EditText editText;
    private Button buttonDelete;
    private Button buttonEdit;
    private Todo todo;

    public static Intent newIntent(Context context, Todo todo) {
        final Intent intent = new Intent(context, EditTodoActivity.class);
        intent.putExtra(EXTRA_TODO, todo);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        buttonEdit = (Button) findViewById(R.id.button_edit);
        buttonEdit.setOnClickListener(clickEditListener);

        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(clickDeleteListener);

        todo = (Todo) getIntent().getSerializableExtra(EXTRA_TODO);
        loading = (ViewGroup) findViewById(R.id.loading);
        editText = (EditText) findViewById(R.id.text);
        editText.setText(todo.getText());

        databaseHelper = new DatabaseHelper(this);
    }

    View.OnClickListener clickEditListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            todo.setText(editText.getText().toString());

            EditTask task = new EditTask();
            task.execute(todo);
        }
    };

    View.OnClickListener clickDeleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DeleteTask task = new DeleteTask();
            task.execute(todo);
        }
    };

    class EditTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected void onPreExecute() {
            loading.setVisibility(View.VISIBLE);
            buttonDelete.setEnabled(false);
            buttonEdit.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Todo... params) {
            databaseHelper.updateTodo(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final Intent intent = new Intent(EditTodoActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Supprime la backstack et lance HomeActivity
            startActivity(intent);
        }
    }

    class DeleteTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected void onPreExecute() {
            loading.setVisibility(View.VISIBLE);
            buttonDelete.setEnabled(false);
            buttonEdit.setEnabled(false);
        }

        @Override
        protected Void doInBackground(Todo... params) {
            databaseHelper.deleteTodo(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            final Intent intent = new Intent(EditTodoActivity.this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Supprime la backstack et lance HomeActivity
            startActivity(intent);
        }
    }
}
