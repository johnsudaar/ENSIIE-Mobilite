package fr.johnsudaar.moviie.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.MovieViewHolderAdapter;
import fr.johnsudaar.moviie.models.Movie;

public class MyMovies extends MenuActivity {
    private String curItem;
    private MovieViewHolderAdapter movieViewHolderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_movies);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        movieViewHolderAdapter= new MovieViewHolderAdapter(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieViewHolderAdapter);
        movieViewHolderAdapter.setMovies(Movie.getAll());

        // Spinner
        ArrayList<String> filters = new ArrayList<>();
        filters.add(getString(R.string.my_movies));
        filters.add(getString(R.string.viewed));
        filters.add(getString(R.string.not_viewed));

        curItem = filters.get(0);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_title, filters);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem =  (String) parent.getAdapter().getItem(position);
                if(selectedItem.equals(getString(R.string.viewed))) {
                    movieViewHolderAdapter.setMovies(Movie.seen());
                } else if(selectedItem.equals(getString(R.string.not_viewed))) {
                    movieViewHolderAdapter.setMovies(Movie.notSeen());
                } else {
                    movieViewHolderAdapter.setMovies(Movie.getAll());
                }
                curItem = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        // Add Movie
        FloatingActionButton addMovieButton = (FloatingActionButton) findViewById(R.id.add_movie_button);
        addMovieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMovies.this, Movies.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart(){
        super.onRestart();
        if(movieViewHolderAdapter == null){
            return;
        }
        if(curItem.equals(getString(R.string.viewed))){
            movieViewHolderAdapter.setMovies(Movie.seen());

        } else if (curItem.equals(getString(R.string.not_viewed))) {
            movieViewHolderAdapter.setMovies(Movie.seen());
        } else {
            movieViewHolderAdapter.setMovies(Movie.getAll());
        }
    }
}
