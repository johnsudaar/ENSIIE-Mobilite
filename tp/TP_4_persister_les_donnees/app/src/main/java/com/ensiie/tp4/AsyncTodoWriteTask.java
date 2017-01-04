package com.ensiie.tp4;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;

import com.ensiie.tp4.database.Todo;

/**
 * Created by john on 13/12/16.
 */

public class AsyncTodoWriteTask extends AsyncTask<Todo, Integer, Integer>{

    private ViewGroup loader;
    private DatabaseHelper dbHelper;
    private Context context;


    public AsyncTodoWriteTask(ViewGroup loader, Context c){
        this.loader = loader;
        this.dbHelper = new DatabaseHelper(c);
        this.context = c;
    }

    @Override
    protected void onPreExecute(){
        this.loader.setVisibility(View.VISIBLE);
    }

    @Override
    protected Integer doInBackground(Todo... todos) {
        dbHelper.addTodo(todos[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Integer res){
        this.loader.setVisibility(View.INVISIBLE);
        Intent myIntent = new Intent(this.context, HomeActivity.class);
        this.context.startActivity(myIntent);
    }
}
