package com.aware.plugin.survey;

import java.util.Calendar;
import java.util.Random;

import android.app.AlarmManager;
import android.support.v4.app.NotificationCompat;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.aware.ESM;
import com.aware.plugin.survey.Alarm;
import com.aware.plugin.survey.ESMstorage;


public class Alarm extends WakefulBroadcastReceiver {

    final public static String ONE_TIME = "onetime";

	public Alarm(){}

	private AlarmManager alarmMgr;
	private PendingIntent alarmIntent;
	private Calendar calendar;
	
	private int range;
	private Random r;
	
	MediaPlayer background;
	AudioManager am;

	public void onReceive(Context context, Intent intent) {   

		Toast t = Toast.makeText(context, "ALARM!!", Toast.LENGTH_LONG);
		t.show();

		Log.i(Plugin.TAG, "Alarm! received");
		setAlarm(context);
		
		//gets current time
		calendar.getInstance();
		
		//checks if it is within 8am - 3am
		if((calendar.HOUR_OF_DAY < 3) || (calendar.HOUR_OF_DAY > 8))
			notifyUser(context);	
	}

	public void setAlarm(Context context) {
		Log.i(Plugin.TAG, "Set Alarm");
		
		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			
		Intent intent = new Intent(context, Alarm.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		r = new Random();
		//range = 1000*60*(r.nextInt(76-45) + 45);
		range = 1000*60*1;
		

		Toast t = Toast.makeText(context, Integer.toString(range), Toast.LENGTH_LONG);
		t.show();
		
		alarmMgr.set(AlarmManager.RTC_WAKEUP, 
				(calendar.getInstance().getTimeInMillis() + range), alarmIntent);

		
		t = Toast.makeText(context, "alarm set", Toast.LENGTH_LONG);
		t.show();
		Log.i(Plugin.TAG, "Alarm Set");
	}


	public void cancelAlarm(Context context) {
		
		if (alarmMgr!= null) {
			alarmMgr.cancel(alarmIntent);
			Toast u = Toast.makeText(context, "alarm cancelled", Toast.LENGTH_LONG);
			u.show();
			Log.i(Plugin.TAG, "Alarm Cancelled");
		}
		
	}
	
	public void doSurvey (Context context) {	

		//Define the ESM to be displayed
		ESMstorage esms = new ESMstorage();
		
		//Queue the ESM to be displayed when possible
		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
		esm.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		
		context.sendBroadcast(esm);	
	}
	
	public void notifyUser(Context context)
	{
		Toast t = Toast.makeText(context, "Notify User", Toast.LENGTH_LONG);
		t.show();
		Log.i(Plugin.TAG, "Notify User");
		
		// prepare intent which is triggered if the
		// notification is selected
		
		int requestID = (int) System.currentTimeMillis();
		
		//Define the ESM to be displayed
		//ESMstorage esms = new ESMstorage();
		//create pending ESM intent  
		//Intent esmIntent = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
	    //esmIntent.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
		
		Intent esmIntent = new Intent(context, ESMstorage.class);
		esmIntent.addFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);

	    PendingIntent pIntent = PendingIntent.getBroadcast(context, requestID, esmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	 
		// build notification
		NotificationCompat.Builder mBuilder =
			    new NotificationCompat.Builder(context)
        			.setSmallIcon(R.drawable.icon_notification)
        			.setContentTitle("SURVEY")
        			.setContentText("Click to start survey.")
        			.setAutoCancel(true);
		
        mBuilder.setContentIntent(pIntent);
		
		// Sets an ID for the notification
		int mNotificationId = (int) System.currentTimeMillis();;
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr = 
		        (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
		
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)
		{
			background = MediaPlayer.create(context, R.raw.success);
			background.setLooping(false);
			background.start();
		}
		vibrator.vibrate(500);
	}
}

