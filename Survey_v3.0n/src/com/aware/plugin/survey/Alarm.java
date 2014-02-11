package com.aware.plugin.survey;

import java.util.Calendar;
import java.util.Random;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
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
	private Calendar calendar;
	
	private int range;
	private Random r;

	@Override
	public void onReceive(Context context, Intent intent) {   

		Toast t = Toast.makeText(context, "ALARM!!", Toast.LENGTH_LONG);
		t.show();
		
		//gets current time
		calendar.getInstance();
		
		//checks if it is within 8am - 3am
		if((calendar.HOUR_OF_DAY < 3) || (calendar.HOUR_OF_DAY > 8))
			notifyUser(context);
		
		setAlarm(context);
	}

	public void setAlarm(Context context) {

		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
	
		Intent intent = new Intent(context, ESMstorage.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		r = new Random();
		range = 1000*60*(r.nextInt(76-45) + 45);
		

		Toast t = Toast.makeText(context, Integer.toString(range), Toast.LENGTH_LONG);
		t.show();
		
		alarmMgr.set(AlarmManager.RTC_WAKEUP, 
				(calendar.getInstance().getTimeInMillis() + range), alarmIntent);
		
		/*
		//set alarm to repeat
	    alarmMgr.setInexactRepeating
		//alarmMgr.setRepeating
		       (AlarmManager.RTC_WAKEUP,  
				System.currentTimeMillis(),
				AlarmManager.INTERVAL_HOUR, 
				//1000*60*3, // 3 minutes, for debugging
				alarmIntent);
				*/
		
		t = Toast.makeText(context, "alarm set", Toast.LENGTH_LONG);
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
	
	public void notifyUser(Context context)
	{
		// prepare intent which is triggered if the
		// notification is selected

		Intent intent = new Intent(context, ESMstorage.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

		// build notification
		// the addAction re-use the same intent to keep the example short
		Notification n  = new Notification.Builder(context)
		        .setContentTitle("SURVEY!!")
		        .setContentText("Click to start stuffs.")
		        .setSmallIcon(R.drawable.image)
		        .setContentIntent(pIntent)
		        .setAutoCancel(true)
		        .build();
		    
		  
		NotificationManager notificationManager = 
		  (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

		notificationManager.notify(0, n); 
	}
}

