package com.ensiie.tp3;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by john on 01/12/16.
 */

public class AsyncTaskExercice4 extends AsyncTask<Integer, Integer, String> {
    private Button b;
    private ProgressBar pb;
    private TextView tv;

    public AsyncTaskExercice4(Button b, ProgressBar pb, TextView tv){
        super();
        this.b = b;
        this.pb = pb;
        this.tv = tv;
    }

    @Override
    protected  void onPreExecute(){
        this.b.setVisibility(View.INVISIBLE);
        this.pb.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(Integer... integers) {

        return Utils.workToDo(integers[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... integers){

    }

    @Override
    protected void onPostExecute(String res){
        this.b.setVisibility(View.VISIBLE);
        this.pb.setVisibility(View.INVISIBLE);
        this.tv.setText(res);
    }
}
