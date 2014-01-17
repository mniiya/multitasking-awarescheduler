package com.aware.plugin.survey;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.aware.ESM;
import com.aware.plugin.survey.ESMstorage;


public class Alarm extends WakefulBroadcastReceiver {

    final public static String ONE_TIME = "onetime";

	public Alarm(){}

	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;

	@Override
	public void onReceive(Context context, Intent intent) {   

		Toast t = Toast.makeText(context, "ALARM!!", Toast.LENGTH_LONG);
		t.show();

		doSurvey(context);
	}

	public void setAlarm(Context context) {

		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	
		Intent intent = new Intent(context, Alarm.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		//set alarm to repeat
	    alarmMgr.setInexactRepeating
		//alarmMgr.setRepeating
		       (AlarmManager.RTC_WAKEUP,  
				System.currentTimeMillis(),
				AlarmManager.INTERVAL_HOUR, 
				//1000*60*3, // 3 minutes
				alarmIntent);
		
		Toast t = Toast.makeText(context, "alarm set", Toast.LENGTH_LONG);
		t.show();
	}


	public void cancelAlarm(Context context) {
		
		if (alarmMgr!= null) {
			alarmMgr.cancel(alarmIntent);
			Toast u = Toast.makeText(context, "alarm cancelled", Toast.LENGTH_LONG);
			u.show();
		}
		
	}
	
	public void doSurvey (Context context) {	
		Vibrator vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
		vibrator.vibrate(500);
		
		//Define the ESM to be displayed
		ESMstorage esms = new ESMstorage();
		
		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
		esm.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		context.sendBroadcast(esm);	
	}
}

