package fr.johnsudaar.moviie.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.models.Movie;

public class MovieDetailActivity extends MenuActivity {

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
}
