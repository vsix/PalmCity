package com.palmcity;

import com.palmcity.util.ImageLoaderUtil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class QiDetailActivity extends BaseActivity {

	ImageView imageView;
	ImageLoaderUtil loader;
	Button productBt;
	Button aboutBt;
	Button contactBt;
	Button concernBt;

	Button collectBt;
	Button likeBt;
	Button shareBt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qi_detail);
		((ImageButton) findViewById(R.id.bt_return))
				.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		loader = new ImageLoaderUtil(this);
		imageView = (ImageView) findViewById(R.id.imageview);
		productBt = (Button) findViewById(R.id.product);
		aboutBt = (Button) findViewById(R.id.about);
		contactBt = (Button) findViewById(R.id.contact);
		concernBt = (Button) findViewById(R.id.concern);
		collectBt = (Button) findViewById(R.id.bt_collect);
		likeBt = (Button) findViewById(R.id.bt_like);
		shareBt = (Button) findViewById(R.id.bt_share);

		productBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.product_ly).getVisibility() == View.VISIBLE) {
					findViewById(R.id.product_ly).setVisibility(View.GONE);
				}else{
					findViewById(R.id.product_ly).setVisibility(View.VISIBLE);
				}
			}
		});
		aboutBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.about_ly).getVisibility() == View.VISIBLE) {
					findViewById(R.id.about_ly).setVisibility(View.GONE);
				}else{
					findViewById(R.id.about_ly).setVisibility(View.VISIBLE);
				}
			}
		});
		contactBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.contact_ly).getVisibility() == View.VISIBLE) {
					findViewById(R.id.contact_ly).setVisibility(View.GONE);
				}else{
					findViewById(R.id.contact_ly).setVisibility(View.VISIBLE);
				}
			}
		});
		concernBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.concern_ly).getVisibility() == View.VISIBLE) {
					findViewById(R.id.concern_ly).setVisibility(View.GONE);
				}else{
					findViewById(R.id.concern_ly).setVisibility(View.VISIBLE);
				}
			}
		});
		collectBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		likeBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
		shareBt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});
	}

}
