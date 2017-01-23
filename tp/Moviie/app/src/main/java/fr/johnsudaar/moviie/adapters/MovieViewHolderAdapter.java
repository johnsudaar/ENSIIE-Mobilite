package fr.johnsudaar.moviie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

import fr.johnsudaar.moviie.EndlessRecyclerViewScrollListener;
import fr.johnsudaar.moviie.MovieViewHolder;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.tasks.MoviesFetcherAsyncTask;
import fr.johnsudaar.moviie.models.Movie;


public class MovieViewHolderAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private ArrayList<Movie> movies;
    private MoviesFetcherAsyncTask fetcher;

    private RecyclerView recyclerView;
    private Button failedButton = null;
    private ProgressBar progressBar;

    private EndlessRecyclerViewScrollListener scrollListener;

    private Context ctx;
    private int tab;
    private int curPage = 0;

    public MovieViewHolderAdapter(Context ctx){
        this.ctx = ctx;
        this.movies  = new ArrayList<Movie>();
    }
    public MovieViewHolderAdapter(Button b, RecyclerView rv, ProgressBar pb, int tab, Context ctx){
        super();
        this.failedButton = b;
        this.recyclerView = rv;
        this.progressBar = pb;
        this.tab = tab;
        this.ctx = ctx;
        this.movies  = new ArrayList<Movie>();
        loadNextData();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.setContext(this.ctx);
        holder.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public void addMovies(ArrayList<Movie> movies){
        if(this.progressBar != null) {
            this.recyclerView.setVisibility(View.VISIBLE);
            this.progressBar.setVisibility(View.INVISIBLE);
            this.failedButton.setVisibility(View.INVISIBLE);
        }
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    public void setFailed(){
        if(this.progressBar != null) {
            this.recyclerView.setVisibility(View.INVISIBLE);
            this.progressBar.setVisibility(View.INVISIBLE);
            this.failedButton.setVisibility(View.VISIBLE);
            this.scrollListener.resetState();
        }
        this.movies.clear();
        this.notifyDataSetChanged();
        this.curPage = 0;
    }

    public void setScrollListener(EndlessRecyclerViewScrollListener sl){
        this.scrollListener = sl;
    }

    public Movie getMovie(int position) {
        return this.movies.get(position);
    }

    public void loadNextData(){
        new MoviesFetcherAsyncTask(tab,this,ctx).execute(++curPage);
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies.clear();
        this.addMovies(movies);
    }

    public void reset(){
        this.curPage = 0;
        this.movies.clear();
    }
}
