package com.palmcity;

import android.os.Environment;

public class Constants {
	
	public static final String DOWNLOADURL = "http://42.121.14.128:8080/jx_app_v2.apk";

	public static final String VERSION_NO = "1.0";

	public static final String ENC = "utf-8";

	public static final String BAIDU_MAP_KEY = "687B688DEC5FFB94B2E6E48C2FAEDA9A471BB2E4";

	public static final String SDCARD_PATH = Environment
			.getExternalStorageDirectory().getPath() + "/";

	public static final String SDCARD_DCIM = SDCARD_PATH + "DCIM/";

	public static final String CAHCE_SDCARD = SDCARD_PATH + "jici/";

	public static String CAHCE_IMAGE = CAHCE_SDCARD + "image/";

//	public static final String DEFAULT_SERVER = "ym.eoooocity.com";
	
//	public static final String DEFAULT_SERVER = "192.168.199.159/jc";
	
	public static final String DEFAULT_SERVER = "42.121.14.128:8081/jc";
	
	public static final String NEW_WEB_URL = "http://42.121.14.128:8080/ys/control/";

	public static final String DEFAULT_WEB_URL = "http://" + DEFAULT_SERVER
			+ "/";

	public static final String SERVER_CACHE_IMAGE = DEFAULT_WEB_URL
			+ "cache/image/";
	
	public static final String SERVER_CACHE_IMAGE2 = "http://42.121.14.128:8080/";

	public static final String COLLECT_URL = DEFAULT_WEB_URL
			+ "collect.action?id=";

	// SHARE PREFERENCES FIELDS
	public static final String SHARE_PREFERENCES = "SHARE_PREFERENCES";
	public static final String ACTION_PACKET_DELETE = "com.lty.swm.packet_del";
	public static final String ACTION_PACKET_STATE = "com.lty.swm.packet_state";
	public static final String ACTION_PACKET_CLEAR = "com.lty.swm.packet_clear";
	public static final String ACTION_RELOAD_CART = "com.lty.swm.reload_cart";
	public static final String ACTION_SHUTDOWN = "com.lty.swm.shutdown";
}
