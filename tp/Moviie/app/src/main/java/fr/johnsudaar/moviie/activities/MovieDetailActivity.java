package fr.johnsudaar.moviie.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.models.Movie;
import fr.johnsudaar.moviie.tasks.FriendSearcherAsyncTask;
import fr.johnsudaar.moviie.tasks.FriendSharerAsyncTask;

public class MovieDetailActivity extends MenuActivity {
    private final static String TAG ="Movie Details";

    private TextView rate;
    private TextView title;
    private TextView synopsis;
    private TextView releaseDate;
    private ImageView imageView;
    private CheckBox seen;
    private FloatingActionButton actionButton;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        rate = (TextView) findViewById(R.id.rated);
        title = (TextView) findViewById(R.id.title);
        synopsis = (TextView) findViewById(R.id.synopsis);
        releaseDate = (TextView) findViewById(R.id.release_date);
        imageView = (ImageView) findViewById(R.id.image_view);
        seen = (CheckBox) findViewById(R.id.seen);
        actionButton = (FloatingActionButton) findViewById(R.id.action_button);

        Bundle b = getIntent().getExtras();

        movie = (Movie) b.getSerializable("movie");

        refresh();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(movie.isInMyMovies()){
                    movie.delete();
                } else {
                    movie.save();
                }
                refresh();
            }
        });

        seen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                movie.setSeen(seen.isChecked());
                movie.save();
                refresh();
            }
        });
    }

    public void refresh(){
        if(! movie.isInMyMovies()) {
            seen.setVisibility(View.INVISIBLE);
        } else {
            seen.setVisibility(View.VISIBLE);
        }

        if(movie.isInMyMovies()) {
            actionButton.setImageResource(R.drawable.ic_delete_black_24dp);
        } else {
            actionButton.setImageResource(android.R.drawable.ic_input_add);
        }

        seen.setChecked(movie.isSeen());

        rate.setText(String.valueOf(movie.getVoteAverage()));
        title.setText(movie.getTitle());
        synopsis.setText(movie.getOverview());
        releaseDate.setText(movie.getReleaseDate());

        Picasso.with(this).load(movie.getBackdropUrl()).into(imageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_share:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                String text = getString(R.string.share_not_seen);
                if (movie.isSeen()) {
                    text = getString(R.string.share_seen);
                }
                text = text.replace("{{MOVIE}}", movie.getTitle());
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setType("text/plain");
                startActivity(intent);
                break;
            case R.id.share_with_friend:
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                b.setTitle("Test");
                b.setItems(Configuration.get().getLoggedInUser().getFriends(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String friend = Configuration.get().getLoggedInUser().getFriends()[i];
                        FriendSharerAsyncTask task = new FriendSharerAsyncTask(movie,getApplicationContext());
                        task.execute(friend);
                    }
                });
                b.show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
