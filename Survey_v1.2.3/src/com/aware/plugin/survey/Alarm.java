package com.aware.plugin.survey;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.util.Calendar;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;

public class Alarm extends WakefulBroadcastReceiver {


	private Plugin plugin;
	private ESMstorage esms;

	public Alarm(Plugin plugin)
	{
		this.plugin = plugin;
		this.plugin.esms = esms;
	}


	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;

	@Override
	public void onReceive(Context context, Intent intent) {   


		Toast t = Toast.makeText(context, "ALARM!!", Toast.LENGTH_LONG);
		t.show();

		//vibrates phone to let user know of incoming ESMs, let me know if you 
		//don't want this feature
		Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
		vibrator.vibrate(500);


		//Make sure ESMs are active within the framework
		Aware.setSetting(plugin.getContentResolver(), Aware_Preferences.STATUS_ESM, true);


		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);

		esm.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		context.sendBroadcast(esm);
	}

	public void setAlarm(Context context) {
		
		Toast t = Toast.makeText(context, "alarm set started", Toast.LENGTH_LONG);
		t.show();
		
		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, Alarm.class);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		
		//set the alarm's time to custom...
		calendar.set(Calendar.HOUR_OF_DAY, 16);
		calendar.set(Calendar.MINUTE, 35);

		//set alarm to repeat
		alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP,  
				calendar.getTimeInMillis(), 
				1000*15,//AlarmManager.INTERVAL_DAY, 
				alarmIntent);
		
		Toast u = Toast.makeText(context, "alarm set finished", Toast.LENGTH_LONG);
		u.show();      
	}


	public void cancelAlarm(Context context) {
		
		
		if (alarmMgr!= null) {
			alarmMgr.cancel(alarmIntent);
		}

	}
}
