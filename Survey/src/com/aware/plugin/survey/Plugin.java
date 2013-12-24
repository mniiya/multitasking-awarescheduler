package com.aware.plugin.survey;

import android.content.Intent;
import android.os.Vibrator;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;
import com.aware.utils.Aware_Sensor;

public class Plugin extends Aware_Sensor {
	@Override
	public void onCreate() {
		super.onCreate();

		//---------------------------------------------------------------------
		//sets up ESM
		//---------------------------------------------------------------------
		//Activates ESM
		Aware.setSetting(getContentResolver(), 
				Aware_Preferences.STATUS_ESM, true);

		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);

		//Initialize Vibrating Thing
		Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		//---------------------------------------------------------------------

		
		//vibrates the device for half a second to notify incoming ESM
		v.vibrate(500);

		//Make sure ESMs are active within the framework
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);
		
		//Define the ESM to be displayed
		String esmString = "[{'esm':{'esm_type':"+ESM.TYPE_ESM_TEXT+"," +
				"'esm_title':'Test Question!!'," +
				"'esm_instructions':'Spam stuff here!'," +
				"'esm_submit':'Submit'," +
				"'esm_expiration_threashold':60," +
				"'esm_trigger':'AWARE Tester'}}]";
		
		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
		esm.putExtra(ESM.EXTRA_ESM, esmString);
		sendBroadcast(esm);		//sends out the ESM as defined in previous lines

		
		//NOTE: This program will only display the ESM once since I can't get the alarm
		//		manager thingy to work yet.  But it should vibrate when it sends out the
		//		ESM, and the window should pop up.  If it doesn't, check if the plugin
		//		is enabled.

	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();

		//Deactivates ESM
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, false);

		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);

	}
}