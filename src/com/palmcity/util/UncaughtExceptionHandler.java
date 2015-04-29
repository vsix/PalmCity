package com.palmcity.util;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.palmcity.Constants;

import android.util.Log;


public class UncaughtExceptionHandler implements
		Thread.UncaughtExceptionHandler {

	private static final String TAG = "UncaughtExceptionHandler";

	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e(TAG, ex.getMessage(), ex);
		try {
			OutputStream output = new FileOutputStream(Constants.CAHCE_SDCARD
					+ "swm.log");
			output.write(ex.getMessage().getBytes());
			output.flush();
			output.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
