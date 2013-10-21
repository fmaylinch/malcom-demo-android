package com.malcom.demo.android;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.activitylifecyclecallbackscompat.MalcomApplicationHelper;

import com.malcom.library.android.MCMDefines;
import com.malcom.library.android.MalcomActivityLifecycleCallbacks;
import com.malcom.library.android.MalcomLib;
import com.malcom.library.android.module.core.MCMCoreAdapter;

public class MalcomDemoApplication extends Application {

    @Override
    public void onCreate() {

        Log.d(MCMDefines.LOG_TAG, "Application onCreate...");

        // Malcom basic init
        String uuid = "60f82918-7dbb-4dae-8c74-3df5701bc1dd";
        String secretKey = "FwP9r1Wa8abK1qQ8MwnSoQ==";
		MalcomLib.initMalcom(this, uuid, secretKey);

		// TODO: Trick to change the malcom api URL (see MCMCoreAdapter.initMalcom)
		// Tip: Use "10.0.2.2" to refer to the computer localhost from the emulator.
		SharedPreferences prefs = getSharedPreferences("MCM_MALCOM_CONFIG", 0);
		prefs.edit().putString(MCMCoreAdapter.PROPERTIES_MALCOM_BASEURL, "http://192.168.2.117:8081/malcom-api/").commit();

		MalcomApplicationHelper.registerActivityLifecycleCallbacks(this, new MalcomActivityLifecycleCallbacks());

        // Added for notifications
		MalcomLib.setSenderId("797542022226");
    }
}
