package com.halbae87.koreanbasicime.setting;

import com.halbae87.koreanbasicime.R;
import com.halbae87.koreanbasicime.Utils.Logs;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
			AlertDialog.Builder dialog = new AlertDialog.Builder(KeyboardSetting.this);
			String items[] = {getString(R.string.pref_danmoum), getString(R.string.pref_dubul)};
			int checkedItem = 0;
			dialog.setTitle(R.string.pref_title_sw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, checkedItem, new selectItem(SettingType.SWKEY_SET))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			dialog.show();
			
		} else if (preference.getKey().equals("setting_hw_key")) {
			Logs.v("hard keyboard setting");
			AlertDialog.Builder dialog = new AlertDialog.Builder(KeyboardSetting.this);
			String items[] = {getString(R.string.pref_sebul), getString(R.string.pref_dubul)};
			int checkedItem = 0;
			dialog.setTitle(R.string.pref_title_hw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, checkedItem, new selectItem(SettingType.HWKEY_SET))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			dialog.show();
		}
		return false;
	}
	

	public class selectItem implements DialogInterface.OnClickListener {

		int mType;
		
		public selectItem(int type) {
			mType = type;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			if (mType == SettingType.SWKEY_SET) {
				Logs.v("(SW)clicked :"+which);
			} else if (mType == SettingType.HWKEY_SET) {
				Logs.v("(HW)clicked :"+which);
			}
		}	
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
