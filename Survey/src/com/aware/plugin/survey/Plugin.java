package com.aware.plugin.survey;

import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;
import com.aware.utils.Aware_Sensor;

public class Plugin extends Aware_Sensor {


	@Override
	public void onCreate() {
		super.onCreate();

		ESMstorage esms = new ESMstorage();
		
		//Alarm a = new Alarm();
		//a.SetAlarm(this);

		
		//Activates ESM
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);

		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);

		//vibrates phone to let user know of incoming ESMs, let me know if you don't want this feature
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(500);

		
		//Make sure ESMs are active within the framework
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);
		
		
		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
		esm.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		sendBroadcast(esm);
		
		 
	}



	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}