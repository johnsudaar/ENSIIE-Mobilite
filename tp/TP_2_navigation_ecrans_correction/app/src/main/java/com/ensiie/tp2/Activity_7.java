package com.ensiie.tp2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Activity_7 extends Activity {

    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice_07);

        title = (TextView) findViewById(R.id.title);
        description = (TextView) findViewById(R.id.description);

        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(checkboxCheckedChangeListener);
    }

    private CompoundButton.OnCheckedChangeListener checkboxCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            title.setVisibility(isChecked ? View.INVISIBLE : View.VISIBLE);
            description.setVisibility(isChecked ? View.VISIBLE : View.GONE);
        }
    };

}
