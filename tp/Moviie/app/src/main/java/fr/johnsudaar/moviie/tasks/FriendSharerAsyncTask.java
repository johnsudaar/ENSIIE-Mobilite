package fr.johnsudaar.moviie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.telecom.Call;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.models.Movie;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FriendSharerAsyncTask extends AsyncTask<String, Void, Boolean> {
    private final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final static OkHttpClient client = new OkHttpClient();

    private final static String TAG="Friend Sharer";
    private Context ctx;
    private Movie movie;

    public FriendSharerAsyncTask(Movie m, Context ctx) {
        this.ctx = ctx;
        this.movie = m;
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String friend = strings[0];
        String url = ctx.getString(R.string.moviieapi_share_url);
        url = url.replace("{{FRIEND}}", friend);
        url = url.replace("{{API_KEY}}", Configuration.get().getLoggedInUser().getApiKey());
        JSONObject movieJson;
        try {
            movieJson = movie.toJsonObject();
        } catch (JSONException e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return false;
        }

        RequestBody body = RequestBody.create(JSON, movieJson.toString());

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
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
}
