package com.halbae87.koreanbasicime.setting;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class Settings {
   	private SharedPreferences mPrefs;
	private SharedPreferences.Editor mPrefEdit;
	private int mSWHanKeyboard;
	private int mHWHanKeyboard;
	private Context mContext;

	
	public Settings(Context context) {
		mContext = context;
		mPrefs = context.getSharedPreferences("setting", Activity.MODE_PRIVATE);
		mSWHanKeyboard = mPrefs.getInt(SettingType.SW_HAN_KEYBOARD, SettingType.HAN_SW_DANMOUM);
		mHWHanKeyboard = mPrefs.getInt(SettingType.HW_HAN_KEYBOARD, SettingType.HAN_HW_SEBUL);
		
	}
	
	public void setHanKeyboard(String type, int value) {
		
    	mPrefs = mContext.getSharedPreferences("setting", Context.MODE_PRIVATE);
    	mPrefEdit = mPrefs.edit();
    	
		mPrefEdit.putInt(type, value);
		mPrefEdit.commit();
	}
	
	public int getHanKeyboard(String type) {
		if (type.equals(SettingType.SW_HAN_KEYBOARD)) {
			return mSWHanKeyboard;
		} else {
			return mHWHanKeyboard;
		}
	}
	
}