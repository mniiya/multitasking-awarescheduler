package com.aware.plugin.survey;

import com.aware.Aware;
import com.aware.Aware_Preferences;
import com.aware.ESM;
import com.aware.utils.Aware_Sensor;

import android.os.Vibrator;

import android.app.Application;
import android.content.Intent;

public class ESMstorage extends Plugin{

	//listing ESMs for now, could be changed to readable from external file
	//instead of being hard coded sometime in the future.
	
	final String ESM1 = "{'esm':" +
			"{'esm_type': 2," +
			"'esm_title':'1/6'," +
			"'esm_instructions':'The user can only choose one option'," +
			"'esm_radios':['In class','Studying','Face-to-Face interaction','None of the above']," +
			"'esm_submit':'Next'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger':'AWARE Tester'}}";
	
	final String ESM2 = "{'esm':" +
			"{'esm_type': 2," +
			"'esm_title':'2/6 (?)'," +
			"'esm_instructions':'The user can only choose one option'," +
			"'esm_radios':['0','1','2','3','4','5','6','7']," +
			"'esm_submit':'Next'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger':'AWARE Tester'}}";

	//switched #2 out with listing 0 - 7
	final String ESM99 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title':'2/6'," +
			"'esm_instructions':'What is your stress level now?'," +
			"'esm_likert_max': 6," +
			"'esm_likert_max_label': 'Highest'," +
			"'esm_likert_min_label': 'Lowest'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger': 'AWARE Tester'}}";

	final String ESM3 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title':'3/6 (?)'," +
			"'esm_instructions':'What is your mood now?'," +
			"'esm_likert_max': 6," +
			"'esm_likert_max_label': 'Positive'," +
			"'esm_likert_min_label': 'Negative'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger': 'AWARE Tester'}}";

	final String ESM4 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title': '4/6'," +
			"'esm_instructions': 'How focused were you in doing what you were just doing?'," +
			"'esm_likert_max': 5," +
			"'esm_likert_max_label': 'extremely'," +
			"'esm_likert_min_label': 'not at all'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger': 'AWARE Tester'}}";

	final String ESM5 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title': '5/6'," +
			"'esm_instructions': 'How challenging was what you were just doing?'," +
			"'esm_likert_max': 5," +
			"'esm_likert_max_label': 'extremely'," +
			"'esm_likert_min_label': 'not at all'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger': 'AWARE Tester'}}";

	final String ESM6 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title': '6/6'," +
			"'esm_instructions': 'How skilled are you at doing what you were just doing?'," +
			"'esm_likert_max': 5," +
			"'esm_likert_max_label': 'extremely'," +
			"'esm_likert_min_label': 'not at all'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 60," +
			"'esm_trigger': 'AWARE Tester'}}";

	//combines all the esms to be displayed.
	final String ALL_ESM = "[" + ESM1 + "," + ESM2 + "," + ESM3 + 
							"," + ESM4 + "," + ESM5 + "," + ESM6 + "]";
	

	//Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

	public void startESM()
	{
		/*
		//v.vibrate(500);

		//Make sure ESMs are active within the framework
		Aware.setSetting(getContentResolver(), Aware_Preferences.STATUS_ESM, true);

		Intent esm = new Intent(ESM.ACTION_AWARE_QUEUE_ESM);

		esm.putExtra(ESM.EXTRA_ESM, ALL_ESM);
		sendBroadcast(esm);
		*/
	}
	
	public ESMstorage()
	{
		//not sure if this is necessary...
	}



}
