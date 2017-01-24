package fr.johnsudaar.moviie.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.FriendAdapter;
import fr.johnsudaar.moviie.tasks.FriendSearcherAsyncTask;

public class FriendSearchActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        final TextView noResults = (TextView) findViewById(R.id.no_results);
        final EditText search = (EditText)  findViewById(R.id.search);
        Button button = (Button) findViewById(R.id.button);
        final FriendAdapter adapter = new FriendAdapter(new String[0], this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(search.getText().length() == 0 ){
                    showToast(getString(R.string.no_empty_search));
                } else {
                    FriendSearcherAsyncTask task = new FriendSearcherAsyncTask(recyclerView,noResults,adapter,progressBar, getApplicationContext());
                    task.execute(search.getText().toString());
                }
            }
        });
    }

    private void showToast(final String value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(FriendSearchActivity.this, value,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
