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
import android.text.GetChars;

public class KeyboardSettingPage extends PreferenceActivity implements  OnPreferenceClickListener{

	private int mSWHanKeyboard;
	private int mHWHanKeyboard;
	private Context mContext;
	private Settings keyboardSetting;
	private DialogInterface mPopupDlg = null;
	Preference mMenuSWKeyboard;
	Preference mMenuHWKeyboard;
	Preference mMenuRefSrc;
	Preference mMenuOpenSrc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.keyboard_setting);
		
		mMenuSWKeyboard = (Preference)findPreference(getText(R.string.key_sw_keyboard));
		mMenuHWKeyboard= (Preference)findPreference(getText(R.string.key_hw_keyboard));
		mMenuRefSrc = (Preference)findPreference(getText(R.string.key_license_refsrc));
		mMenuOpenSrc = (Preference)findPreference(getText(R.string.key_license_opensrc));
		
		mMenuSWKeyboard.setOnPreferenceClickListener(this);
		mMenuHWKeyboard.setOnPreferenceClickListener(this);
		mMenuRefSrc.setOnPreferenceClickListener(this);
		mMenuOpenSrc.setOnPreferenceClickListener(this);

		mContext = this;
		keyboardSetting = new Settings(mContext);
		mSWHanKeyboard= keyboardSetting.getHanKeyboard(getString(R.string.key_sw_keyboard));
		mHWHanKeyboard= keyboardSetting.getHanKeyboard(getString(R.string.key_hw_keyboard));
		
		setSummarySWKeyboard(mSWHanKeyboard);
		setSummaryHWKeyboard(mHWHanKeyboard);
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		AlertDialog.Builder dialog = new AlertDialog.Builder(KeyboardSettingPage.this);
		Logs.d("onPreferenceClick()");
		if (preference.getKey().equals(getString(R.string.key_sw_keyboard))) {
			Logs.v("soft keyboard setting");
			
			String items[] = {getString(R.string.pref_danmoum), getString(R.string.pref_dubul)};
			dialog.setTitle(R.string.pref_title_sw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, mSWHanKeyboard, new selectItem(preference.getKey()))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			mPopupDlg= dialog.show();
			return true;
			
		} else if (preference.getKey().equals(getString(R.string.key_hw_keyboard))) {
			Logs.v("hard keyboard setting");
			String items[] = {getString(R.string.pref_sebul), getString(R.string.pref_dubul)};
			dialog.setTitle(R.string.pref_title_hw_keyboard);
			
			dialog
			.setSingleChoiceItems(items, mHWHanKeyboard, new selectItem(preference.getKey()))
			.setNegativeButton(R.string.dialog_btn_no, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Logs.v("취소버튼 눌렀네");
				}
			});
			
			mPopupDlg = dialog.show();
			return true;
		} else if (preference.getKey().equals(getString(R.string.key_license_refsrc))) {
			return true;
		} else if (preference.getKey().equals(getString(R.string.key_license_refsrc))) {
			return true;
		} else {
			return false;
		}
		
	}
	

	/**
	 * 
	 * @author Hong.SeongChan
	 *
	 */
	public class selectItem implements DialogInterface.OnClickListener {
		
		String mType;
		
		public selectItem(String type) {
			mType = type;
		}
		
		@Override
		public void onClick(DialogInterface dialog, int which) {
			keyboardSetting.setHanKeyboard(mType, which);
		
			if (mType.equals(getString(R.string.key_sw_keyboard))) {
				mSWHanKeyboard = which;
				setSummarySWKeyboard(which);
			} else if (mType.equals(getString(R.string.key_hw_keyboard))) {
				mHWHanKeyboard = which;
				setSummaryHWKeyboard(which);
			}
			
			mPopupDlg.dismiss();
		}	
	} 

	
	/**
	 * 키보드 설정값을 세팅한다.
	 * @param type
	 * @param value
	 */
	public void setKeyboard(String type, int value) {
		keyboardSetting.setHanKeyboard(type, value);
	}
	
	
	/**
	 * 한글키보드 설정값을 summary에 노출한다.
	 * @param type
	 */
	public void setSummarySWKeyboard(int type) {
		if (mSWHanKeyboard == SettingType.HAN_SW_DANMOUM) {
			mMenuSWKeyboard.setSummary(getText(R.string.pref_danmoum));
		} else {
			mMenuSWKeyboard.setSummary(getText(R.string.pref_dubul));
		}
		
	}
	
	/**
	 * 외장한글키보드 설정값을 summary에 노출한다.
	 * @param type
	 */
	public void setSummaryHWKeyboard(int type) {
		if (mHWHanKeyboard == SettingType.HAN_HW_SEBUL) {
			mMenuHWKeyboard.setSummary(getText(R.string.pref_sebul));
		} else {
			mMenuHWKeyboard.setSummary(getText(R.string.pref_dubul));
		}
	}
 }
