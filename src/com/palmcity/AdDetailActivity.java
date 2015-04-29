package com.palmcity;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.palmcity.util.ImageLoaderUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AdDetailActivity extends BaseActivity {
	
	ImageLoaderUtil loader;
	Button collectBt;
	Button likeBt;
	Button shareBt;
	ImageView imageContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_detail);
		((ImageButton) findViewById(R.id.bt_return)).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		loader = new ImageLoaderUtil(this);
		imageContent = (ImageView) findViewById(R.id.imagecontent);
		collectBt = (Button) findViewById(R.id.bt_collect);
		likeBt = (Button) findViewById(R.id.bt_like);
		shareBt = (Button) findViewById(R.id.bt_share);
		
	}
	
	private void loading(){
		client.get("", new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
