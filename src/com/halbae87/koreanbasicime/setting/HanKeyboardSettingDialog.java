package com.halbae87.koreanbasicime.setting;

import com.halbae87.koreanbasicime.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HanKeyboardSettingDialog extends Activity {
	public String keyType;
	public TextView firstString;
	public TextView secondString;
	public int selectType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hankeyboard_select);
		
		firstString = (TextView)findViewById(R.id.first);
		secondString = (TextView)findViewById(R.id.second);
		
		String selectType = getIntent().getStringExtra(SettingType.HANKEYBOARD);
		if (selectType.equals(SettingType.SWKEY_SET)) {
			firstString.setText(R.string.pref_danmoum);
			secondString.setText(R.string.pref_dubul);
		} else {
			firstString.setText(R.string.pref_sebul);
			secondString.setText(R.string.pref_dubul);
		}
		
	}

}
