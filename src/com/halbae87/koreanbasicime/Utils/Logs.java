package com.halbae87.koreanbasicime.Utils;

import android.util.Log;

public class Logs {
	static String TAG = "KOREA_IME";
	static boolean DEBUG = true;
	
	
	public static void v(String msg) {
		if (DEBUG) {
			Log.v(TAG, msg);
		} else {
			return;
		}
	}
	
	public static void d(String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
		} else {
			return;
		}
	}
}
