package com.malcom.demo.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.malcom.library.android.MalcomLib;

public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

		initButtons();
    }

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }


	private void initButtons() {

		initEventButtons();
		initTagButtons();
	}

	private void initEventButtons() {

		final Button startEventButton = (Button) findViewById(R.id.startEventButton);
		startEventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String event = getEvent();
				MalcomLib.startEvent(event);
			}
		});

		final Button endEventButton = (Button) findViewById(R.id.endEventButton);
		endEventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String event = getEvent();
				MalcomLib.endEvent(event);
			}
		});
	}

	private void initTagButtons() {

		final Button addTagButton = (Button) findViewById(R.id.addTagButton);
		addTagButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String tag = getTag();
				MalcomLib.addTag(tag);
			}
		});

		final Button removeTagButton = (Button) findViewById(R.id.removeTagButton);
		removeTagButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String tag = getTag();
				MalcomLib.removeTag(tag);
			}
		});
	}

	private String getEvent() {
		return getEditTextString(R.id.eventText);
	}

	private String getTag() {
		return getEditTextString(R.id.tagText);
	}

	private String getEditTextString(int editTextId) {
		final EditText eventText = (EditText) findViewById(editTextId);
		return eventText.getText().toString();
	}
}
