package fr.johnsudaar.moviie.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
import fr.johnsudaar.moviie.database.DatabaseHelpler;
import fr.johnsudaar.moviie.models.Movie;
import fr.johnsudaar.moviie.models.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class LoginActivity extends AppCompatActivity {

    /*
        The login activity is a bit (lot) messy. This activity should manage the data loading.

        If the user was previously connected, we will only fetch his username from the remote API
        and trust our database for the other data.

        If the user is not connected, he can register or login.

        When a user has been successfully logged in, we drop the current database and replace it
        with the one sent by the server.
     */

    final static String TAG ="LOGIN";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private RelativeLayout content;
    private ProgressBar progressBar;
    private EditText password;
    private EditText login;
    private View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showProgress();
            final JSONObject jsonBody = new JSONObject();
            try {
                jsonBody.put("username", login.getText().toString());
                jsonBody.put("password", password.getText().toString());
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e.getCause());
            }


            RequestBody body = RequestBody.create(JSON, jsonBody.toString());

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(getString(R.string.moviieapi_login_url))
                    .post(body)
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, e.getMessage(), e.getCause());
                    sendToast(getString(R.string.server_error));
                    showContent();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        JSONObject jsonUser;
                        try {
                            jsonUser = new JSONObject(response.body().string());
                            loadUser(jsonUser);
                        } catch (JSONException e) {
                            Log.e(TAG, e.getMessage(), e.getCause());
                            sendToast(getString(R.string.server_error));
                        }

                    } else {
                        sendToast(getString(R.string.invalid_credentials));
                    }

                    showContent();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = (Button) findViewById(R.id.register_button);
        Button loginButton = (Button) findViewById(R.id.login_button);
        login = (EditText) findViewById(R.id.login_field);
        password = (EditText) findViewById(R.id.password_field);
        content = (RelativeLayout) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        // Loading from shared preferences

        SharedPreferences pref = getSharedPreferences("moviie", MODE_PRIVATE);
        String apiKey = pref.getString("API_KEY", null);
        //if(apiKey != null){
            //showProgress();
        //} else {
            showContent();
            loginButton.setOnClickListener(loginClickListener);

            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        //}
    }


    public void sendToast(final String toast){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUser(JSONObject jsonUser) throws JSONException {
        JSONArray jsonFriends = jsonUser.getJSONArray("friends");
        String[] friends = new String[jsonFriends.length()];
        for (int i = 0; i < jsonFriends.length(); i++) {
            friends[i] = jsonFriends.getString(i);
        }
        User user = new User(jsonUser.getString("username"),
                jsonUser.getString("api_key"),
                friends);
        Configuration.get().setLoggedInUser(user);

        // Well thats hacky (I need to reset the database). TODO: Clean that mess up
        Configuration.get().getDbHelper().onUpgrade(Configuration.get().getDbHelper().getDb(),0,DatabaseHelpler.DATABASE_VERSION);

        // Loading movies
        Movie.clearCache();
        if(!jsonUser.isNull("movies")){
            JSONArray movies = jsonUser.getJSONArray("movies");
            for (int i = 0; i < movies.length(); i++) {
                Movie m = new Movie(movies.getJSONObject(i));
                m.save();
            }
        }


        getSharedPreferences("moviie", MODE_PRIVATE)
                .edit()
                .putString("API_KEY", user.getApiKey())
                .apply();

        Intent intent = new Intent(LoginActivity.this, MyMovies.class);
        startActivity(intent);
    }

    private void showContent(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                content.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void showProgress(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                content.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
