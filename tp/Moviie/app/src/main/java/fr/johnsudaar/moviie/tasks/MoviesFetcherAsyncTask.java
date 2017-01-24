package fr.johnsudaar.moviie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.MovieViewHolderAdapter;
import fr.johnsudaar.moviie.models.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MoviesFetcherAsyncTask extends AsyncTask<Integer, Void, ArrayList<Movie>> {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String TAG = "MoviesFetcher";

    private Context ctx;
    private int tab;
    private MovieViewHolderAdapter adapter;



    public MoviesFetcherAsyncTask(int tab, MovieViewHolderAdapter adapter, Context c) {
        this.ctx     = c;
        this.tab     = tab;
        this.adapter = adapter;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected ArrayList<Movie> doInBackground(Integer... integers) {
        int page = integers[0];
        String url = getUrl(page);
        Request request = new Request.Builder()
                .url(url)
                .build();

        JSONObject mainObj = null;
        JSONArray mainArray = null;
        try{
            Response response = client.newCall(request).execute();
            if(tab == 3) {
                mainArray = new JSONArray(response.body().string());
            } else {
                mainObj = new JSONObject(response.body().string());
            }
        }catch (Exception e) {
            Log.e(TAG,e.getMessage(), e.getCause());
            return null;
        }

        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {
            JSONArray moviesArray = null;
            if(tab == 3) {
                moviesArray = mainArray;
            } else {
                moviesArray = mainObj.getJSONArray("results");
            }
            for(int i = 0; i < moviesArray.length(); i++) {
                JSONObject movieObj = null;
                if(tab == 3) {
                    movieObj = moviesArray.getJSONObject(i).getJSONObject("movie");
                } else {
                    movieObj = moviesArray.getJSONObject(i);
                }
                Movie m = Movie.findById(movieObj.getLong("id"));
                if (m == null) {
                    m = new Movie();
                    m.setId(movieObj.getLong("id"));
                    m.setTitle(movieObj.getString("title"));
                    if (tab == 3) {
                        m.setBackdrop(movieObj.getString("backdrop"));
                        m.setPoster(movieObj.getString("poster"));
                    } else {
                        m.setBackdrop(movieObj.getString("backdrop_path"));
                        m.setPoster(movieObj.getString("poster_path"));
                    }
                    m.setOverview(movieObj.getString("overview"));
                    m.setReleaseDate(movieObj.getString("release_date"));
                    m.setVoteAverage(movieObj.getDouble("vote_average"));
                }
                movies.add(m);
            }
        }catch(Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return null;
        }
        return movies;
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> results) {
        if(results == null) {
            this.adapter.setFailed();
        } else {
            this.adapter.addMovies(results);
        }
    }

    private String getUrl(int page){
        String url = "";
        switch(tab) {
            case 0:
                url = ctx.getString(R.string.themoviedb_poular_url);
                break;
            case 1 :
                url = ctx.getString(R.string.themoviedb_rated_url);
                break;
            case 2 :
                url = ctx.getString(R.string.themoviedb_coming_url);
                break;
            case 3 :
                url = ctx.getString(R.string.moviieapi_my_share_url);
                break;
        }
        url = url.replace("{{API_KEY}}", ctx.getString(R.string.themoviedb_api_key));
        url = url.replace("{{PAGE}}", Integer.toString(page));
        url = url.replace("{{YEAR}}", Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
        url = url.replace("{{USER_API_KEY}}", Configuration.get().getLoggedInUser().getApiKey());
        Log.i(TAG,url);
        return url;
    }
}
