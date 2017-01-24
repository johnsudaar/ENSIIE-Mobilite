package fr.johnsudaar.moviie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.models.Movie;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MovieSenderAsyncTask extends AsyncTask<Void, Void, Boolean> {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String TAG = "Movie sender";
    private final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    private Context ctx;
    private Callable<Void> end;

    public MovieSenderAsyncTask(Context c, Callable<Void> callable){
        this.ctx = c;
        this.end = callable;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String url = ctx.getString(R.string.moviieapi_send_movies_url);
        url = url.replace("{{API_KEY}}", Configuration.get().getLoggedInUser().getApiKey());

        JSONArray mainArray = new JSONArray();

        ArrayList<Movie> movies = Movie.getAll();
        try {
            for (Movie m : movies) {
                mainArray.put(m.toJsonObject());
            }
        }catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return false;
        }

        RequestBody body = RequestBody.create(JSON, mainArray.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Log.i(TAG, url);
        Log.i(TAG,mainArray.toString());

        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()){
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return false;
        }
        return false;
    }
    
    @Override
    protected void onPostExecute(Boolean res){
        if(res){
            try {
                end.call();
            } catch (Exception e) {
                Log.e(TAG,e.getMessage(), e.getCause());
            }
        } else {
            Toast.makeText(ctx, ctx.getString(R.string.unable_logout), Toast.LENGTH_SHORT).show();
        }
    }
}
