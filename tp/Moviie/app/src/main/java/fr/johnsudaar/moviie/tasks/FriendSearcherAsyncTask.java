package fr.johnsudaar.moviie.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.adapters.FriendAdapter;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FriendSearcherAsyncTask  extends AsyncTask<String, Void, String[]>{
    private final static OkHttpClient client = new OkHttpClient();
    private final static String TAG = "FriendSearcher";

    private Context ctx;
    private RecyclerView recyclerView;
    private TextView noResult;
    private FriendAdapter adapter;
    private ProgressBar progressBar;

    public FriendSearcherAsyncTask(RecyclerView r, TextView t, FriendAdapter a, ProgressBar p, Context c) {
        this.recyclerView = r;
        this.noResult = t;
        this.adapter = a;
        this.progressBar = p;
        this.ctx = c;
    }

    @Override
    protected void onPreExecute(){
        progressBar.setVisibility(View.VISIBLE);
        noResult.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String[] doInBackground(String... strings) {
        String url = ctx.getString(R.string.moviieapi_search_url);
        url = url.replace("{{PATTERN}}", strings[0]);
        Request request = new Request.Builder()
                .url(url)
                .build();
        Log.i(TAG, url);
        JSONArray mainObj;
        try {
            Response response = client.newCall(request).execute();
            mainObj = new JSONArray(response.body().string());
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return null;
        }

        String[] result;
        try {
            result = new String[mainObj.length()];
            for (int i = 0; i < mainObj.length(); i++) {
                result[i] = mainObj.getString(i);
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e.getCause());
            return null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String[] result){
        progressBar.setVisibility(View.INVISIBLE);
        if(result.length == 0) {
            noResult.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            noResult.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setData(result);
        }
    }
}
