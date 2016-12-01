package com.ensiie.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.ensiie.tp3.bo.Neighbourhood;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice13_1Activity extends AppCompatActivity implements Callback {
	private static final String TAG     = "Activity8";
	private static final int    ERROR   = 0;
	private static final int    SUCCESS = 1;

	private RecyclerView             recyclerView;
	private ProgressBar              progressBar;
	private Button                   button;
	private ArrayList<Neighbourhood> neighbourhoods;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ERROR:
					showError();
					break;
				case SUCCESS:
					neighbourhoods = (ArrayList<Neighbourhood>) msg.obj;
					fillList();
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercice_13_1);

		final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setLayoutManager(linearLayoutManager);

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(onClickListener);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);

		callWebservice();
	}

	private void showError() {
		button.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
	}

	private void fillList() {
		button.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);

		final Exercice13Adapter adapter = new Exercice13Adapter(neighbourhoods);
		adapter.setListener(itemClickListener);

		recyclerView.setAdapter(adapter);
	}

	Exercice13Adapter.MyItemClickListener itemClickListener = new Exercice13Adapter.MyItemClickListener() {
		@Override
		public void onItemClickListener(int position) {
			final Intent intent = new Intent(Exercice13_1Activity.this, Exercice13_2Activity.class);
			intent.putExtra(Exercice13_2Activity.EXTRA_NEIGHBOURHOOD, neighbourhoods.get(position));
			startActivity(intent);
		}
	};

	View.OnClickListener onClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
            button.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
			callWebservice();
		}
	};

	// region Webservice

	private void callWebservice() {
		Request request = new Request.Builder()
				.url("https://data.police.uk/api/leicestershire/neighbourhoods")
				.build();
		Webservice.getOkHttpClient().newCall(request).enqueue(this);
	}

	@Override
	public void onFailure(Call call, IOException e) {
		handler.sendEmptyMessage(ERROR);
	}

	@Override
	public void onResponse(Call call, Response response) throws IOException {
		if (!response.isSuccessful()) {
			handler.sendEmptyMessage(ERROR);
		}

		try {
			ArrayList<Neighbourhood> neighbourhoods = parserResponse(response);
			final Message message = Message.obtain();
			message.what = SUCCESS;
			message.obj = neighbourhoods;
			handler.sendMessage(message);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			handler.sendEmptyMessage(ERROR);
		}
	}

	private ArrayList<Neighbourhood> parserResponse(Response response) throws IOException, JSONException {
		final ArrayList<Neighbourhood> neighbourhoods = new ArrayList<>();
		final JSONArray neighbourhoodsArr = new JSONArray(response.body().string());
		for (int i = 0; i < neighbourhoodsArr.length(); i++) {
			final JSONObject neighbourhoodObj = neighbourhoodsArr.getJSONObject(i);
			final String id = neighbourhoodObj.getString("id");
			final String name = neighbourhoodObj.getString("name");
			neighbourhoods.add(new Neighbourhood(id, name));
		}

		return neighbourhoods;
	}

	// endregion
}
