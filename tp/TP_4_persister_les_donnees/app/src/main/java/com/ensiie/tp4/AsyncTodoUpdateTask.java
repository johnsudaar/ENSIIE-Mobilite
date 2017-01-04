package com.ensiie.tp4;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import com.ensiie.tp4.database.Todo;

/**
 * Created by john on 13/12/16.
 */

public class AsyncTodoUpdateTask extends AsyncTask<Todo, Void, Void> {

    private Context c;
    private ProgressBar pb;

    public AsyncTodoUpdateTask(Context c, ProgressBar pb){
        this.c = c;
        this.pb = pb;
    }

    @Override
    protected Void doInBackground(Todo... todos) {
        return null;
    }

    @Override
    protected void onPreExecute(){
        this.pb.setVisibility(View.VISIBLE);
    }

}
