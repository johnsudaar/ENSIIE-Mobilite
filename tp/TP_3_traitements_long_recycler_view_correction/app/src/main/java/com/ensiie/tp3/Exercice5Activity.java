package com.ensiie.tp3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Adrian on 19/11/2016.
 */
public class Exercice5Activity extends AppCompatActivity {
    private ProgressBar progressBar;
    private Button button;
    private TextView percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_task);

        percent = (TextView) findViewById(R.id.text);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonClickListener);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final MyAsyncTask asyncTask = new MyAsyncTask();
            asyncTask.execute();
        }
    };

    private class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            button.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            percent.setText(values[0] + "%");
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 1; i < 11; i++) {
                Utils.smallWorkToDo();
                publishProgress(i * 10);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            button.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }
}

