package com.ensiie.tp4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ensiie.tp4.database.Todo;


public class AddTodoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_todo);

		Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText text = (EditText) findViewById(R.id.text);
                ViewGroup vg = (ViewGroup) findViewById(R.id.loading);
                AsyncTodoWriteTask writeTask = new AsyncTodoWriteTask(vg, AddTodoActivity.this);
                writeTask.execute(new Todo(text.getText().toString()));
            }
        });
	}
}
