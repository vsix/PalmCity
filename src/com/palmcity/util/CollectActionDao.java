package com.palmcity.util;

import com.loopj.android.http.AsyncHttpClient;

public class CollectActionDao {

	AsyncHttpClient client;
	CollectActionResultCallback resultCallback;
	
	public interface CollectActionResultCallback {
		public void onSuccess(String str);
		public void onFail(int errorCode);
	}
	
	public CollectActionDao(int id,int type,CollectActionResultCallback resultCallback){
		client = new AsyncHttpClient();
		this.resultCallback = resultCallback;
	}
}
