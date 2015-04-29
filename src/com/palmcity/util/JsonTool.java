package com.palmcity.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;


/**
 * 获取服务端JSON数据
 * 
 * @author litianyu
 * 
 */
public class JsonTool {

	/**
	 * 获取服务端数据
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONArray getArrayContent(String domain, String url)
			throws Exception {
		return new JSONArray(getJson(domain, url));
	}

	/**
	 * 获取服务端数据
	 * 
	 * @param serverDomain
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getContent(String domain, String url)
			throws Exception {
		return new JSONObject(getJson(domain, url));
	}

	public static String getJson(String domain, String url) throws Exception {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpParams httpParams = client.getParams();
		// 设置网络超时参数
		HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
		HttpConnectionParams.setSoTimeout(httpParams, 10000);
		HttpResponse response = client.execute(new HttpGet(domain + url));
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent(), "utf-8"), 8192);
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line + "\n");
			}
			reader.close();
		}
		Log.d("", domain + url);
		Log.d("", builder.toString());
		return builder.toString();
	}
}
