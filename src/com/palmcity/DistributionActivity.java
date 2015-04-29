package com.palmcity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class DistributionActivity extends Activity {
	
	Button fenleiBt;
	ImageView imageView;
	Button photoBt;
	EditText contentEd;
	EditText addressEd;
	Button locationBt;
	EditText phoneEd;
	EditText phoneEd2;
	Button saveBt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distribution);
		((ImageButton) findViewById(R.id.bt_return))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		fenleiBt = (Button) findViewById(R.id.bt_fenlei);
		imageView = (ImageView) findViewById(R.id.imageview);
		photoBt = (Button) findViewById(R.id.bt_photo);
		contentEd = (EditText) findViewById(R.id.ed_content);
		addressEd = (EditText) findViewById(R.id.ed_address);
		locationBt = (Button) findViewById(R.id.bt_location);
		phoneEd = (EditText) findViewById(R.id.ed_phone);
		phoneEd2 = (EditText) findViewById(R.id.ed_phone1);
		saveBt = (Button) findViewById(R.id.bt_save);
	}
}
