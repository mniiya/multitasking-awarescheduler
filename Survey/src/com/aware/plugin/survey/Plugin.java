package com.aware.plugin.survey;

import android.content.Intent;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.plugin.survey.Alarm;

import com.aware.utils.Aware_Sensor;

public class Plugin extends Aware_Sensor {

	
	//private WakefulBroadcastReceiver alarm = new Alarm(this);
	private Alarm a;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		//Logcat label
		TAG = "Survey";
		
		//Use the users’ debug preference from AWARE
		DEBUG = Aware.getSetting(getContentResolver(), Aware_Preferences.DEBUG_FLAG).equals("true");
		
		//Make sure ESMs are active within the framework
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);
		//Request AWARE to apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);
		
		a = new Alarm();
		a.initialize(getApplicationContext());
		a.setAlarm(getApplicationContext());
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		//Set ESM sensor as deactivated
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, false);
		//Request AWARE to apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);
		
		// Careful!   This clears ALL databases of ALL plugins when the Survey plugin is disabled.  Use only for debugging.  Keep commented!
        //Intent esmClearDB = new Intent(Aware.ACTION_AWARE_CLEAN_DATABASES);
		//sendBroadcast(esmClearDB);

		a.cancelAlarm(getApplicationContext());
	}
}
