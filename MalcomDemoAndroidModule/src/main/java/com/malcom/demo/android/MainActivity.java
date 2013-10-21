package com.malcom.demo.android;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.malcom.library.android.MCMDefines;
import com.malcom.library.android.MalcomLib;
import com.malcom.library.android.module.notifications.EnvironmentType;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		Log.d(MCMDefines.LOG_TAG, "onCreate " + this);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.d(MCMDefines.LOG_TAG, "intent of this activity " + getIntent());

		initDemoThings();
	}

	@Override
	protected void onStart() {

		Log.d(MCMDefines.LOG_TAG, "onStart " + this);

		super.onStart();
	}

	@Override
	protected void onResume() {

		Log.d(MCMDefines.LOG_TAG, "onResume " + this);

		super.onResume();

		// TODO: From a certain Android version you should perform file system operations from a separate thread
		// http://stackoverflow.com/questions/13323431/strictmodediskreadviolation-when
		Log.d(MCMDefines.LOG_TAG, "Registering");
		MalcomLib.notificationsRegister(this, EnvironmentType.SANDBOX, "New message", true, MainActivity.class);

		Log.d(MCMDefines.LOG_TAG, "Check new notifications");
		MalcomLib.checkForNewNotifications(this);
	}

	@Override
	protected void onPause() {

		Log.d(MCMDefines.LOG_TAG, "onPause " + this);

		super.onPause();
	}

	@Override
	protected void onStop() {

		Log.d(MCMDefines.LOG_TAG, "onStop " + this);

		super.onStop();
	}

	@Override
	protected void onDestroy() {

		Log.d(MCMDefines.LOG_TAG, "onDestroy " + this);

		GCMRegistrar.onDestroy(getApplicationContext());
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// This method seems to be called when you open the notification while already being in this activity
	@Override
	protected void onNewIntent(Intent intent) {

		Log.d(MCMDefines.LOG_TAG, "onNewIntent " + this);
		setIntent(intent); // If you want to display the notification even when the activity is already on top
	}

	private void initDemoThings() {

		final MainActivity that = this;

		final Button showInfoButton = (Button) findViewById(R.id.showInfoButton);
		showInfoButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView infoText = (TextView) findViewById(R.id.infoText);
				// TODO: Display useful info here
				infoText.setText("No info collected so far.\nWait for the developer to work a little more!");
			}
		});

		final Button openActivityButton = (Button) findViewById(R.id.openActivityButton);
		openActivityButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent infoActivityIntent = new Intent(that, InfoActivity.class);
				startActivity(infoActivityIntent);
			}
		});
	}
}
