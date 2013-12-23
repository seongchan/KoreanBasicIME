package com.halbae87.koreanbasicime.setting;

import com.halbae87.koreanbasicime.R;
import com.halbae87.koreanbasicime.Utils.Logs;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class KeyboardSetting extends PreferenceActivity implements  OnPreferenceClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.keyboard_setting);
		
		Preference swKeyboard = (Preference)findPreference("setting_sw_key");
		Preference hwKeyboard = (Preference)findPreference("setting_hw_key");
		
		swKeyboard.setOnPreferenceClickListener(this);
		hwKeyboard.setOnPreferenceClickListener(this);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		if (preference.getKey().equals("setting_sw_key")) {
			Logs.v("soft keyboard setting");
			Intent intent = new Intent(this, HanKeyboardSettingDialog.class);
			intent.putExtra(SettingType.HANKEYBOARD, SettingType.SWKEY_SET);
			startActivityForResult(intent, SettingType.SWKEY_REQUEST_CODE);
			
		} else if (preference.getKey().equals("setting_hw_key")) {
			Logs.v("hard keyboard setting");
			Intent intent = new Intent(this, HanKeyboardSettingDialog.class);
			intent.putExtra(SettingType.HANKEYBOARD, SettingType.HWKEY_SET);
			startActivityForResult(intent, SettingType.HWKEY_REQUEST_CODE);
		}
		return false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		switch (resultCode) {
			case RESULT_OK :
				String result = data.getExtras().getString("setting");
				switch (requestCode) {
				case 1234 :  // SWKEY_REQUEST_CODE
					Logs.v("SW Setting is "+ result);
					break;
				case 5678 :  // HWKEY_REQUEST_CODE
					Logs.v("HW Setting is "+ result);
					break;
				}
			default :
				super.onActivityResult(requestCode, resultCode, data);
				break;
		}

	}

	

}
