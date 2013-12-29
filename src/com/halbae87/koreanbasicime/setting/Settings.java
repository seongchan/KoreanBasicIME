/**
 * Copyright (C) 2013 Hong.SeongChan.
 */
package com.halbae87.koreanbasicime.setting;

import com.halbae87.koreanbasicime.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author Hong,SeongChan
 *
 */
public class Settings {
   	private SharedPreferences mPrefs;
	private SharedPreferences.Editor mPrefEdit;
	private int mSWHanKeyboard;
	private int mHWHanKeyboard;
	private Context mContext;

	/**
	 * Settings Class Contstructor.
	 * @param context
	 */
	public Settings(Context context) {
		mContext = context;
		mPrefs = context.getSharedPreferences("setting", Activity.MODE_PRIVATE);
		mSWHanKeyboard = mPrefs.getInt(mContext.getString(R.string.key_sw_keyboard), SettingType.HAN_SW_DANMOUM);
		mHWHanKeyboard = mPrefs.getInt(mContext.getString(R.string.key_hw_keyboard), SettingType.HAN_HW_SEBUL);
	}
	
	/**
	 * 키보드 설정값을 Preference에 저장한다. 
	 * @param type
	 * @param value
	 */
	public void setHanKeyboard(String type, int value) {
		
    	mPrefs = mContext.getSharedPreferences("setting", Context.MODE_PRIVATE);
    	mPrefEdit = mPrefs.edit();
    	
		mPrefEdit.putInt(type, value);
		mPrefEdit.commit();
	}
	
	/**
	 * 키보드 설정값을 반환한다. 
	 * int getHanKeyboard()
	 * @param type
	 * @return
	 */
	public int getHanKeyboard(String type) {
		if (type.equals(mContext.getString(R.string.key_sw_keyboard))) {
			return mSWHanKeyboard;
		} else if (type.equals(mContext.getString(R.string.key_hw_keyboard))) {
			
			return mHWHanKeyboard;
		} 
		
		return 0;
	}
	
}
