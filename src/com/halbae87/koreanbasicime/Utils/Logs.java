/**
 * Copyright (C) 2013 Hong.SeongChan.
 **/

package com.halbae87.koreanbasicime.Utils;

import android.util.Log;

/**
 * Logs Class. (로그출력 관리용 wrapper)
 * @author Hong,SeongChan
 */
public class Logs {
	static String TAG = "KOREANBASIC_IME";
	static boolean DEBUG = true;	// 로그를 출력하지 않으려면, false로 변경한다.
	
	
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
