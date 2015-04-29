package com.palmcity;

import com.loopj.android.http.AsyncHttpClient;
import com.palmcity.util.UncaughtExceptionHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class BaseActivity extends Activity {
//	protected SharedPreferences sharedPrefs;
//	protected ServerDao serverDao;
//	protected SqliteDao sqliteDao;
	// protected Progressbase_dialog progressbase_dialog;
	AlertDialog base_dialog;
	Context context;
	AsyncHttpClient client;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
//		sharedPrefs = getSharedPreferences(Constants.SHARE_PREFERENCES, 0);
//		serverDao = new ServerDaoImpl(sharedPrefs);
//		sqliteDao = new SqliteDaoImpl(this);
		client = new AsyncHttpClient();
		context = this;
	}

	public void toast(String text, int duration) {
		// Toast toast = new Toast(getApplicationContext());
		// View toastView = View.inflate(getApplicationContext(),
		// R.layout.toast,
		// null);
		// toast.setView(toastView);
		// toast.setGravity(Gravity.BOTTOM, 0, 50);
		// toast.setDuration(duration);
		// ((TextView)
		// toastView.findViewById(R.id.toast_content)).setText(text);
		// toast.show();
		Toast toast = Toast.makeText(getApplicationContext(), text, duration);
		toast.show();
	}

	public void showProgressDialog() {
		try {
			// if (this.progressbase_dialog == null) {
			// this.progressbase_dialog = new Progressbase_dialog(this);
			// }
			// this.progressbase_dialog.setTitle(getString(R.string.loadTitle));
			// // this.progressbase_dialog.setMessage("正在加载......");
			// this.progressbase_dialog.show();
//			if(base_dialog != null){
				LayoutInflater inflater = LayoutInflater.from(context);
				View layout = inflater.inflate(R.layout.progress_loading,null);
				base_dialog = new AlertDialog.Builder(context).create();
				base_dialog.setView(layout, 0, 0, 0, 0);				
//			}
			base_dialog.show();
		} catch (Exception e) {

		}
	}

	public void closeProgressDialog() {
		try {
//			if (this.progressbase_dialog != null)
//				this.progressbase_dialog.dismiss();
			if(base_dialog!=null){
				base_dialog.dismiss();
			}
		} catch (Exception e) {

		}
	}
}
