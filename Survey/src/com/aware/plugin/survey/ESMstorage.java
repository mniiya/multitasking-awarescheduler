package com.aware.plugin.survey;

import com.aware.ESM;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ESMstorage extends BroadcastReceiver {

	//buffer
	final String ESM0 = "{'esm':" +
			"{'esm_type': 5," +
			"'esm_title': 'Survey'," +
			"'esm_instructions': 'Press Next to start...'," +
			"'esm_quick_answers': ['Next']," +
			"'esm_expiration_threashold': 2699," +
			"'esm_trigger': 'AWARE Tester'}}";
	
	final String ESM1 = "{'esm':" +
			"{'esm_type': 2," +
			"'esm_title':'1/6'," +
			"'esm_instructions':'What are you doing right now?'," +
			"'esm_radios':['In class','Studying','Face-to-Face interaction','None of the above']," +
			"'esm_submit':'Next'," +
			"'esm_expiration_threashold': 2699," +
			"'esm_trigger':'AWARE Tester'}}";

	final String ESM2 = "{'esm':" +
			"{'esm_type': 2," +
			"'esm_title':'2/6'," +
			"'esm_instructions':'What is your stress level right now?'," +
			"'esm_radios':['1 (lowest)','2','3','4','5','6','7 (highest)']," +
			"'esm_submit':'Next'," +
			"'esm_expiration_threashold': 2699," +
			"'esm_trigger':'AWARE Tester'}}";

	final String ESM3 = "{'esm':" +
			"{'esm_type': 2," +
			"'esm_title':'3/6'," +
			"'esm_instructions':'What is your mood right now?'," +
			"'esm_radios':['1 (negative)','2','3','4','5','6','7 (positive)']," +
			"'esm_submit':'Next'," +
			"'esm_expiration_threashold': 2699," +
			"'esm_trigger':'AWARE Tester'}}";

	final String ESM4 = "{'esm':" +
			"{'esm_type': 4," +
			"'esm_title': '4/6'," +
			"'esm_instructions': 'How focused were you in doing what you were just doing?'," +
			"'esm_likert_max': 5," +
			"'esm_likert_max_label': 'extremely'," +
			"'esm_likert_min_label': 'not at all'," +
			"'esm_likert_step': 1," +
			"'esm_submit': 'OK'," +
			"'esm_expiration_threashold': 2699," +
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
			"'esm_expiration_threashold': 2699," +
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
			"'esm_expiration_threashold': 2699," +
			"'esm_trigger': 'AWARE Tester'}}";

	//combines all the esms to be displayed.
	final String ALL_ESM = "[" + ESM0 + "," + ESM1 + "," + ESM2 + "," + ESM3 + 
			"," + ESM4 + "," + ESM5 + "," + ESM6 + "]";

}
