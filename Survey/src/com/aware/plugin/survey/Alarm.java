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
	
	NotificationCompat.Builder mBuilder;
	NotificationManager mNotifyMgr;
	int mNotificationId;
	boolean first;

	public void onReceive(Context context, Intent intent) {   

		Log.i(Plugin.TAG, "Alarm! received");
		setAlarm(context);
		
		//gets current time
		calendar = calendar.getInstance();
		
		int hour = calendar.get(calendar.HOUR_OF_DAY);
		
		//checks if it is within 8am - 3am
		if((hour < 3) || (hour > 8))
			notifyUser(context);	
	}

	public void setAlarm(Context context) {
		Log.i(Plugin.TAG, "Set Alarm");
		
		alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
			
		Intent intent = new Intent(context, Alarm.class);
		intent.putExtra(ONE_TIME, Boolean.FALSE);
		alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
		
		r = new Random();
		range = 1000*60*(r.nextInt(76-45) + 45);
		//range = 1000*60*1;

		
		alarmMgr.set(AlarmManager.RTC_WAKEUP, 
				(calendar.getInstance().getTimeInMillis() + range), alarmIntent);

		Log.i(Plugin.TAG, "Alarm Set");
	}


	public void cancelAlarm(Context context) {
		
		if (alarmMgr!= null) {
			alarmMgr.cancel(alarmIntent);
			Log.i(Plugin.TAG, "Alarm Cancelled");
		}	
	}
	
	public void initialize(Context context) {
		
		mNotificationId = 0;
		first = true;
	}
	
	
	public void notifyUser(Context context)
	{
		Log.i(Plugin.TAG, "Notify User");
		
		//sets up ESM intent
		//---------------------------------------------------------------------
		//Define the ESM to be displayed
		ESMstorage esms = new ESMstorage();
		//create pending ESM intent  
		Intent esmIntent = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);
	    esmIntent.putExtra(ESM.EXTRA_ESM, esms.ALL_ESM);
	    
		//Intent esmIntent = new Intent(context, ESMstorage.class);
		//esmIntent.addFlags(Intent.FLAG_DEBUG_LOG_RESOLUTION);
	    //----------------------------------------------------------------------

	    int requestID = (int) System.currentTimeMillis();
	    PendingIntent pIntent = PendingIntent.getBroadcast(context, requestID, esmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	 
		// build notification
		mBuilder = new NotificationCompat.Builder(context)
        			.setSmallIcon(R.drawable.icon_notification)
        			.setContentTitle("SURVEY")
        			.setContentText("Click to start survey.")
        			.setAutoCancel(true);
		
        mBuilder.setContentIntent(pIntent);
		
        
		// Sets an ID for the notification
		mNotificationId = (int) System.currentTimeMillis();		
		// Gets an instance of the NotificationManager service
		mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		
		
		//cancels previous notification
		if(!(first))
			mNotifyMgr.cancelAll();
		
		// Builds the notification and issues it.
		mNotifyMgr.notify(mNotificationId, mBuilder.build());
		
		//notification stuffs
		//----------------------------------------------------------------------
		Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		
		if(am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL)
		{
			background = MediaPlayer.create(context, R.raw.success);
			background.setLooping(false);
			background.start();
		}
		vibrator.vibrate(500);
		//----------------------------------------------------------------------
		
		//sets first notification check as false
		first = false;
	}
}

