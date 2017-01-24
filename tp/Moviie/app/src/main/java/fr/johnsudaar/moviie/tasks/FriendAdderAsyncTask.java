package fr.johnsudaar.moviie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.FriendAdapter;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class FriendAdderAsyncTask extends AsyncTask<String, Void, String> {
    private final static OkHttpClient client = new OkHttpClient();
    private final static String TAG = "FriendAdder";
    private final static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private Context ctx;
    private FriendAdapter adapter;

    public FriendAdderAsyncTask(Context c, FriendAdapter a){
        this.ctx = c;
        this.adapter = a;
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = ctx.getString(R.string.moviieapi_friend_add_url);
        url = url.replace("{{FRIEND}}", strings[0]);
        url = url.replace("{{API_KEY}}", Configuration.get().getLoggedInUser().getApiKey());
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON,"{}"))
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return null;
        }

        if(response.isSuccessful()) {
            return strings[0];
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String res) {
        if(res != null){
            Configuration.get().getLoggedInUser().addFriend(res);
            adapter.notifyDataSetChanged();
        }
    }
}
