package com.ensiie.tp3;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by john on 01/12/16.
 */

public class AsyncTaskExercice5 extends AsyncTask<Integer, Integer, Integer> {
    private Button b;
    private ProgressBar pb;
    private TextView tv;

    public AsyncTaskExercice5(Button b, ProgressBar pb, TextView tv){
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
    protected Integer doInBackground(Integer... integers) {
        for(int i = 0; i < 11; i++)  {
            this.publishProgress(i);
            Utils.smallWorkToDo();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... integers){
        this.tv.setText(""+integers[0]*10);
    }

    @Override
    protected void onPostExecute(Integer res){
        this.b.setVisibility(View.VISIBLE);
        this.pb.setVisibility(View.INVISIBLE);
    }
}
