package com.ensiie.tp3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice12Activity extends AppCompatActivity implements Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Request request = new Request.Builder()
                .url("https://www.google.com")
                .build();
        Webservice.getOkHttpClient().newCall(request).enqueue(this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
    }
}
