package fr.johnsudaar.moviie.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.johnsudaar.moviie.Configuration;
import fr.johnsudaar.moviie.R;
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
    final static String TAG ="LOGIN";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registerButton = (Button) findViewById(R.id.register_button);
        Button loginButton = (Button) findViewById(R.id.login_button);
        final EditText login = (EditText) findViewById(R.id.login_field);
        final EditText password = (EditText) findViewById(R.id.password_field);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("username", login.getText().toString());
                    jsonBody.put("password", password.getText().toString());
                }catch(JSONException e) {
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
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()) {
                            JSONObject jsonUser;
                            try{
                                jsonUser = new JSONObject(response.body().string());
                                JSONArray jsonFriends = jsonUser.getJSONArray("friends");
                                String[] friends = new String[jsonFriends.length()];
                                for(int i = 0; i < jsonFriends.length(); i++){
                                    friends[i] = jsonFriends.getString(i);
                                }
                                User user = new User(jsonUser.getString("username"),
                                                    jsonUser.getString("api_key"),
                                                    friends);
                                Configuration.get().setLoggedInUser(user);
                                Intent intent = new Intent(LoginActivity.this, MyMovies.class);
                                startActivity(intent);
                            } catch (JSONException e) {
                                Log.e(TAG, e.getMessage(), e.getCause());
                                sendToast(getString(R.string.server_error));
                            }

                        } else {
                            sendToast(getString(R.string.invalid_credentials));
                        }
                    }
                });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    public void sendToast(final String toast){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
