package com.ensiie.tp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ensiie.tp3.bo.Neighbourhood;
import com.ensiie.tp3.bo.NeighbourhoodDetail;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Adrian on 29/11/2016.
 */
public class Exercice13_2Activity extends AppCompatActivity implements Callback {
	public static final  String EXTRA_NEIGHBOURHOOD = "extra_neighbourhood";
	private static final String TAG                 = "Activity8";
	private static final int    ERROR               = 0;
	private static final int    SUCCESS             = 1;

	private ViewGroup           content;
	private ProgressBar         progressBar;
	private Button              retry;
	private NeighbourhoodDetail neighbourhoodDetail;
	private Neighbourhood neighbourhood;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case ERROR:
					showError();
					break;
				case SUCCESS:
					neighbourhoodDetail = (NeighbourhoodDetail) msg.obj;
					fillView();
					break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercice_13_2);
		neighbourhood = (Neighbourhood) getIntent().getSerializableExtra(EXTRA_NEIGHBOURHOOD);

		retry = (Button) findViewById(R.id.retry);
		retry.setOnClickListener(retryClickListener);

		final Button facebook = (Button) findViewById(R.id.facebook);
		facebook.setOnClickListener(facebookClickListener);

		final Button mail = (Button) findViewById(R.id.email);
		mail.setOnClickListener(mailClickListener);

		final Button phone = (Button) findViewById(R.id.phone);
		phone.setOnClickListener(phoneClickListener);

		content = (ViewGroup) findViewById(R.id.content);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);

		callWebservice(neighbourhood.getId());
	}

	private void showError() {
		retry.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
	}

	private void fillView() {
		content.setVisibility(View.VISIBLE);
		retry.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);

		final TextView name = (TextView) findViewById(R.id.name);
		final TextView description = (TextView) findViewById(R.id.description);

		name.setText(neighbourhoodDetail.getName());
		description.setText(neighbourhoodDetail.getDescription());
	}

	View.OnClickListener facebookClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(neighbourhoodDetail.getFacebook()));
			startActivity(intent);
		}
	};

	View.OnClickListener phoneClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			final Intent intent = new Intent(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:" + neighbourhoodDetail.getTelephone()));
			startActivity(intent);
		}
	};

	View.OnClickListener mailClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			final Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ neighbourhoodDetail.getEmail() });
			intent.setType("message/rfc822");
			startActivity(intent);
		}
	};

	View.OnClickListener retryClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
            retry.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

			callWebservice(neighbourhood.getId());
		}
	};

	// region Webservice

	private void callWebservice(String id) {
		Request request = new Request.Builder()
				.url("https://data.police.uk/api/leicestershire/" + id)
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
			NeighbourhoodDetail neighbourhoodDetail = parserResponse(response);
			final Message message = Message.obtain();
			message.what = SUCCESS;
			message.obj = neighbourhoodDetail;
			handler.sendMessage(message);
		} catch (JSONException e) {
			Log.e(TAG, e.getMessage());
			handler.sendEmptyMessage(ERROR);
		}
	}

	private NeighbourhoodDetail parserResponse(Response response) throws IOException, JSONException {
		final JSONObject neighbourhoodObj = new JSONObject(response.body().string());
		final String name = neighbourhoodObj.getString("name");
		final String description = neighbourhoodObj.getString("description");
		final JSONObject neighbourhoodContactObj = neighbourhoodObj.getJSONObject("contact_details");
		final String facebook = neighbourhoodContactObj.getString("facebook");
		final String phone = neighbourhoodContactObj.getString("telephone");
		final String email = neighbourhoodContactObj.getString("email");
		return new NeighbourhoodDetail(name, description, facebook, phone, email);
	}

	// endregion
}
