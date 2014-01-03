package com.aware.plugin.survey;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;
import com.aware.utils.Aware_Sensor;

public class Plugin extends Aware_Sensor {

	//private WakefulBroadcastReceiver alarm = new Alarm(this);
	private Alarm a = new Alarm(this);
	ESMstorage esms = new ESMstorage();

	@Override
	public void onCreate() {
		super.onCreate();

		//Activates ESM
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);

		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);



		//---------------------------------------------------------------------
		//to test alarm, use this code...
		//some stuff in alarm is temporarily modified...
		//---------------------------------------------------------------------
		a.setAlarm(getApplicationContext());
		//---------------------------------------------------------------------



		//esms.startESM(this);
		//doStuffs();


		//---------------------------------------------------------------------
		//receive ESM data?
		//---------------------------------------------------------------------
		//
		//---------------------------------------------------------------------

	}



	@Override
	public void onDestroy() {
		super.onDestroy();

		//Deactivate sensors
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, false);

		//Apply settings
		Intent applySettings = new Intent(Aware.ACTION_AWARE_REFRESH);
		sendBroadcast(applySettings);
	}

	public void doStuffs()
	{
		//---------------------------------------------------------------------
		//doing this temporarily just to show all the ESMs on startup.
		//---------------------------------------------------------------------
		

		//vibrates phone to let user know of incoming ESMs, let me know if you 
		//don't want this feature
		Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		vibrator.vibrate(500);


		//Make sure ESMs are active within the framework
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);


		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);

		//for some reason, can't call esms.startESM() without crashing program...
		esm.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		sendBroadcast(esm);
		//---------------------------------------------------------------------

	}
}