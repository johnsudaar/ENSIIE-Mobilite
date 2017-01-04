package com.ensiie.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

public class TodoListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_todo);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_view);
        ProgressBar pb = (ProgressBar)findViewById(R.id.progress_bar);
        AsyncTodoFetchTask asyncTodoFetchTask = new AsyncTodoFetchTask(rv,pb,this);
        asyncTodoFetchTask.execute();
    }
}
