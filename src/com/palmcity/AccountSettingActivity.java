package com.palmcity;


import java.io.File;

import com.palmcity.util.Bimp;
import com.palmcity.util.ImageLoaderUtil;
import com.palmcity.util.SelectPicPopupWindow;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AccountSettingActivity extends BaseActivity {
	
	SelectPicPopupWindow menuWindow;
	String pathName;
	Bitmap bitmap1;
	ImageView imageView;
	ImageLoaderUtil loader;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_setting);
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
		imageView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				menuWindow = new SelectPicPopupWindow(
						AccountSettingActivity.this, itemsOnClick);
				menuWindow.showAtLocation(AccountSettingActivity.this
						.findViewById(R.id.account_mainly),
						Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
						0);
			}
		});
	}
	
	private OnClickListener itemsOnClick = new OnClickListener() {

		public void onClick(View v) {
			menuWindow.dismiss();
			switch (v.getId()) {
			case R.id.btn_take_photo:
				getPictureC();
				break;
			case R.id.btn_pick_photo:
				getPictureP();
				break;
			default:
				break;
			}

		}
	};
	
	public void getPictureC() {
		try {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			pathName =Environment
					.getExternalStorageDirectory().getPath() + "/" + "swm_touxiang" + ".jpg";
			File out = new File(pathName);
			Uri uri = Uri.fromFile(out);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
			startActivityForResult(intent, 101);
		} catch (Exception e) {
			Toast.makeText(AccountSettingActivity.this, "相机初始化失败",
					Toast.LENGTH_SHORT).show();
			Log.e("", e.getMessage(), e);
		}
	}

	// 相册获取照片
	public void getPictureP() {
		try {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(intent, 100);
		} catch (Exception e) {
			Toast.makeText(AccountSettingActivity.this, "相册初始化失败",
					Toast.LENGTH_SHORT).show();
			Log.e("", e.getMessage(), e);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("", "onActivityResult...");
		if (resultCode == RESULT_OK) {
			try {
				try {
					if (bitmap1 != null) {
						bitmap1.recycle();
						bitmap1 = null;
					}
					if (requestCode == 100) {
						
						Uri uri = data.getData();
//						ContentResolver cr = getContentResolver();
//						bitmap1 = BitmapFactory.decodeStream(cr.openInputStream(uri));
						
						String[] proj = {MediaStore.Images.Media.DATA};
						 
			            //好像是android多媒体数据库的封装接口，具体的看Android文档
			            Cursor cursor = managedQuery(uri, proj, null, null, null); 
			            //按我个人理解 这个是获得用户选择的图片的索引值
			            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
			            //将光标移至开头 ，这个很重要，不小心很容易引起越界
			            cursor.moveToFirst();
			            //最后根据索引值获取图片路径
			            String path = cursor.getString(column_index);
			            bitmap1 = Bimp.revitionImageSize(path);
					} else if (requestCode == 101) {
						bitmap1 = Bimp.revitionImageSize(pathName);
					}
					if (bitmap1 != null) {
						imageView.setImageBitmap(bitmap1);
					} else {
						Toast.makeText(this, "无法获取图片，请横向拍摄", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {

				}
				
			} catch (Exception e) {
				Log.e("", e.getMessage(), e);
			}
		} else {
			Toast.makeText(this, "无法获取图片，请横向拍摄", Toast.LENGTH_SHORT).show();
		}
	}
}
