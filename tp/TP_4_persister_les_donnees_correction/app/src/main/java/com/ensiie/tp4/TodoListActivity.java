package com.ensiie.tp4;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.ensiie.tp4.database.DatabaseHelper;
import com.ensiie.tp4.database.Todo;

import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class TodoListActivity extends Activity {

    private DatabaseHelper databaseHelper;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_todo);

        databaseHelper = new DatabaseHelper(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ListTask listTask = new ListTask();
        listTask.execute();
    }

    private TodoAdapter.TodoListener listener = new TodoAdapter.TodoListener() {
        @Override
        public void onTodoSelected(Todo todo) {
            startActivity(EditTodoActivity.newIntent(TodoListActivity.this, todo));
        }
    };

    class ListTask extends AsyncTask<Void, Void, ArrayList<Todo>> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<Todo> doInBackground(Void[] params) {
            return databaseHelper.getAllTodos();
        }

        @Override
        protected void onPostExecute(ArrayList<Todo> res) {
            progressBar.setVisibility(View.GONE);

            TodoAdapter adapter = new TodoAdapter(res);
            adapter.setListener(listener);

            recyclerView.setAdapter(adapter);
        }
    }
}
