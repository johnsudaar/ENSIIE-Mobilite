package com.ensiie.tp4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.ensiie.tp4.database.Todo;

import java.util.ArrayList;

/**
 * Created by john on 13/12/16.
 */

public class AsyncTodoFetchTask extends AsyncTask<Void, Integer, ArrayList<Todo>>{

    private DatabaseHelper db;
    private RecyclerView recyclerView;
    private ProgressBar pb;
    private Context c;

    public AsyncTodoFetchTask(RecyclerView view, ProgressBar pb, Context c){
        this.db = new DatabaseHelper(c);
        this.recyclerView = view;
        this.pb = pb;
        this.c = c;
    }

    @Override
    protected void onPreExecute(){
        this.pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<Todo> doInBackground(Void... voids) {
        return this.db.getTodos();
    }

    @Override
    protected void onPostExecute(ArrayList<Todo> todos){
        this.pb.setVisibility(View.INVISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(layoutManager);

        TodoAdapter adapter = new TodoAdapter(todos);
        this.recyclerView.setAdapter(adapter);

        adapter.setListener(new TodoAdapter.TodoListener() {
            @Override
            public void onTodoSelected(Todo todo) {
                Intent myIntent = new Intent(AsyncTodoFetchTask.this.c, EditTodoActivity.class);
                myIntent.putExtra("todo", todo);
                AsyncTodoFetchTask.this.c.startActivity(myIntent);
            }
        });

    }
}
