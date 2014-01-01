package com.aware.plugin.survey;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver 
{    
	@Override
	public void onReceive(Context context, Intent intent) 
	{   
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
		wl.acquire();

		//some test stuffs to check if the alarm is working
		Toast.makeText(context, "Alarm!!", Toast.LENGTH_LONG).show();
		Log.d("ALARM!!", "Alarm's going off! :D");
		
		//commented out because i can't even test the alarm...
		//ESMstorage asdf = new ESMstorage();
		//asdf.startESM();

		wl.release();
	}

	public void SetAlarm(Context context)
	{
		//for debugging...
		Toast t = Toast.makeText(context, "Alarm set...", Toast.LENGTH_SHORT);
		t.show();
		Log.d("ALARM!!", "SetAlarm() executed!");
		
		
		AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent i = new Intent(context, Alarm.class);
		PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);
		am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 
				1000 * 30, pi); // Millisec * Second, should go off every 20 seconds, for checking
	}

	public void CancelAlarm(Context context)
	{
		//for debugging...
		Log.d("ALARM!!", "CancelAlarm() executed!");
		
		
		Intent intent = new Intent(context, Alarm.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
	}

}