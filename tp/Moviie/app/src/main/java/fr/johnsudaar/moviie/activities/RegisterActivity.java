package fr.johnsudaar.moviie.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import fr.johnsudaar.moviie.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    public final String TAG = "REGISTER";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText login    = (EditText) findViewById(R.id.login_field);
        final EditText password = (EditText) findViewById(R.id.password_field);
        final EditText confirm  = (EditText) findViewById(R.id.confirmation_field);

        Button registerButton = (Button) findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG,"SALUT");
                if(! password.getText().toString().equals(confirm.getText().toString())) {
                    Log.i(TAG,"MARCHE PAS !!!");

                    RegisterActivity.this.showToast(getString(R.string.password_do_not_match));
                } else {
                    OkHttpClient client = new OkHttpClient();

                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("username", login.getText().toString());
                        jsonBody.put("password", password.getText().toString());
                    }catch(JSONException e) {
                        Log.e(TAG, e.getMessage(), e.getCause());
                    }


                    RequestBody body = RequestBody.create(JSON, jsonBody.toString());

                    Request request = new Request.Builder()
                            .url(getString(R.string.moviieapi_register_url))
                            .post(body)
                            .build();

                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            RegisterActivity.this.showToast(getString(R.string.server_error));
                            Log.e(TAG,e.getMessage(), e.getCause());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if(! response.isSuccessful()) {
                                RegisterActivity.this.showToast(getString(R.string.username_already_exists));
                            } else {
                                RegisterActivity.this.showToast(getString(R.string.registration_successfull));
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    private void showToast(final String toast) {
        Log.i(TAG,toast);
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(RegisterActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
