package com.ensiie.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ensiie.tp4.database.Todo;

public class EditTodoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_todo);

		Todo todo = (Todo) getIntent().getSerializableExtra("todo");
		EditText content = (EditText) findViewById(R.id.text);
		content.setText(todo.getText());

		Button btn = (Button) findViewById(R.id.button_edit);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

			}
		});

	}
}
