package com.ensiie.tp3;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by john on 01/12/16.
 */

public class AsyncTaskExercice3 extends AsyncTask<Integer, Integer, Integer> {
    private Button b;
    private ProgressBar pb;

    public AsyncTaskExercice3(Button b, ProgressBar pb){
        super();
        this.b = b;
        this.pb = pb;
    }

    @Override
    protected  void onPreExecute(){
        this.b.setVisibility(View.INVISIBLE);
        this.pb.setVisibility(View.INVISIBLE);
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        Utils.workToDo();
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... integers){

    }

    @Override
    protected void onPostExecute(Integer res){
        this.b.setVisibility(View.VISIBLE);
        this.pb.setVisibility(View.VISIBLE);
    }
}
