package fr.johnsudaar.moviie.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;
import java.util.concurrent.Callable;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.tasks.MovieSenderAsyncTask;


public class MenuActivity extends AppCompatActivity{
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.about:
                intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.friends:
                intent = new Intent(this, FriendsActivity.class);
                startActivity(intent);
                break;
            case R.id.logout:
                logout();
                break;
        }
        return true;
    }

    public void logout(){
        MovieSenderAsyncTask task = new MovieSenderAsyncTask(this.getApplicationContext(), new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                getSharedPreferences("moviie", MODE_PRIVATE)
                        .edit()
                        .remove("API_KEY")
                        .apply();
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
                return null;
            }
        });
        Toast.makeText(this, getString(R.string.loging_out), Toast.LENGTH_SHORT).show();
        task.execute();
    }
}
