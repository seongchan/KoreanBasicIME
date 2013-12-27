/**
 * Copyright (C) 2013 Hong.SeongChan.
 **/

package com.halbae87.koreanbasicime.setting;

import com.halbae87.koreanbasicime.R;
import com.halbae87.koreanbasicime.Utils.Logs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class KeyboardSettingPage extends PreferenceActivity implements  OnPreferenceClickListener{

	private int mSWHanKeyboard;
	private int mHWHanKeyboard;
	private Context mContext;
	private Settings keyboardSetting;
	private DialogInterface mPopupDlg = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.keyboard_setting);
		
		Preference swKeyboard = (Preference)findPreference(SettingType.SW_HAN_KEYBOARD);
		Preference hwKeyboard = (Preference)findPreference(SettingType.HW_HAN_KEYBOARD);
		
		swKeyboard.setOnPreferenceClickListener(this);
		hwKeyboard.setOnPreferenceClickListener(this);

		mContext = this;
		keyboardSetting = new Settings(mContext);
		mSWHanKeyboard= keyboardSetting.getHanKeyboard(SettingType.SW_HAN_KEYBOARD);
		mHWHanKeyboard= keyboardSetting.getHanKeyboard(SettingType.HW_HAN_KEYBOARD);
		Logs.d("Setting Value(SW)"+mSWHanKeyboard);
		Logs.d("Setting Value(SW)"+mHWHanKeyboard);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		
		Logs.d("onPreferenceClick()");
		if (preference.getKey().equals("setting_sw_key")) {
			Logs.v("soft keyboard setting");
			AlertDialog.Builder dialog = new AlertDialog.Builder(KeyboardSettingPage.this);
			String items[] = {getString(R.string.pref_danmoum), getString(R.string.pref_dubul)};
			dialog.setTitle(R.string.pref_title_sw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, mSWHanKeyboard, new selectItem(SettingType.SW_HAN_KEYBOARD))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			mPopupDlg= dialog.show();
			
		} else if (preference.getKey().equals("setting_hw_key")) {
			Logs.v("hard keyboard setting");
			AlertDialog.Builder dialog = new AlertDialog.Builder(KeyboardSettingPage.this);
			String items[] = {getString(R.string.pref_sebul), getString(R.string.pref_dubul)};
			dialog.setTitle(R.string.pref_title_hw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, mHWHanKeyboard, new selectItem(SettingType.HW_HAN_KEYBOARD))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			mPopupDlg = dialog.show();
		}
		return false;
	}
	

	public class selectItem implements DialogInterface.OnClickListener {
		
		String mType;
		
		public selectItem(String type) {
			mType = type;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			keyboardSetting.setHanKeyboard(mType, which);
			
			
			if (mType == SettingType.SW_HAN_KEYBOARD) {
				Logs.v("(SW)clicked :"+which);
				mSWHanKeyboard = which;
			} else if (mType == SettingType.HW_HAN_KEYBOARD) {
				mHWHanKeyboard = which;
				Logs.v("(HW)clicked :"+which);
			}
			mPopupDlg.dismiss();
			
		}	
	} 
	
	
	public void setKeyboard(String type, int value) {
		keyboardSetting.setHanKeyboard(type, value);
	}
 }
