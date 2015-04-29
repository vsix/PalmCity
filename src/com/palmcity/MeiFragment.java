package com.palmcity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.palmcity.adapter.HuiAdAdapter;
import com.palmcity.adapter.MeiListAdapter;
import com.palmcity.adapter.StoreListAdapter;
import com.palmcity.vo.HuiAdVo;
import com.palmcity.vo.MeiListVo;
import com.palmcity.vo.StoreListVo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class MeiFragment extends Fragment{
	// protected Progressbase_dialog progressbase_dialog;
	AlertDialog base_dialog;
	Context context;
	List<HuiAdVo> items;
	private HuiAdAdapter adapter;
	Gallery viewFlipper;
	List<ImageView> mSlideViews;
	boolean isPlay = false;
	Timer timer;  
	
	private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	MeiListAdapter gridAdapter;
	int page = 1;
	List<MeiListVo> gridItems;
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		// TODO Auto-generated method stub
		if (isVisibleToUser) {
			
		} else {
			// 不可见时不执行操作
			
		}
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		timer = new Timer();
		timer.schedule(new TimerTask() {				
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(100);
				Log.e("","msg");
			}
		}, 6000, 6000);
		Log.e("",	 "begin");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		if(timer!=null){
			timer.cancel();
		}			
		Log.e("",	 "stop");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		items = new ArrayList<HuiAdVo>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_mei, container,
				false);
		mSlideViews = new ArrayList<ImageView>();
		mSlideViews.add((ImageView) v.findViewById(R.id.image1));
		mSlideViews.add((ImageView) v.findViewById(R.id.image2));
		mSlideViews.add((ImageView) v.findViewById(R.id.image3));
		mSlideViews.add((ImageView) v.findViewById(R.id.image4));
		mSlideViews.add((ImageView) v.findViewById(R.id.image5));
		adapter = new HuiAdAdapter(getActivity());
		viewFlipper = (Gallery) v.findViewById(R.id.flip);
		loading();
		
		mPullRefreshGridView = (PullToRefreshGridView) v.findViewById(R.id.pull_refresh_grid);
		gridItems = new ArrayList<MeiListVo>();
		gridAdapter = new MeiListAdapter(getActivity());
		mGridView = mPullRefreshGridView.getRefreshableView();
		mPullRefreshGridView.setMode(Mode.BOTH);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<GridView> refreshView) {
				items.clear();
				page = 1;
				loading();
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<GridView> refreshView) {
				loading();
			}

			/*@Override
			public void onRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				new GetDataTask().execute();
			}*/

		});
		mGridView.setAdapter(gridAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				/*ServerLiveShow temp = (ServerLiveShow) adapter.getItem(arg2);
				Intent intent = new Intent(getActivity(), CustomeTwoActivity.class);
				Bundle extras = new Bundle();
				extras.putString("id", temp.getId());
				extras.putString("typeName", temp.getName());
				extras.putString("typeId", "");
				intent.putExtras(extras);
				startActivity(intent);getActivity().overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);*/
			}
		});
		
		return v;
	}
	
	private void loading() {
		if(items.size()!=0){
			return;
		}
		new Thread() {
			public void run() {
//				List<ServerNewsList> list = new ArrayList<ServerNewsList>();
//				try {
//					String url = "ser_newsList.action?typeId=1&page=" + 1
//							+ "&pageSize=" + 10;
//					String objs = JsonTool.getJson(Constants.NEW_WEB_URL,
//							url);
//					JSONObject obj = new JSONObject(objs);
//					Editor editor = sharedPrefs.edit();
//					editor.putString("mainNewsOne", objs);
//					editor.commit();
//					
//					Gson gson = new Gson();
//					list = gson.fromJson(obj.getString("newslist"), new TypeToken<List<ServerNewsList>>(){}.getType());
//					
//					JSONArray array = obj.getJSONArray("newslist");
//					int len = array.length();
//					if (len > 4) {
//						len = 5;
//					}
//					for (int i = 0; i < len; i++) {
//						JSONObject aobj = array.getJSONObject(i);
//						ServerNewsList item = new ServerNewsList(aobj);
//						list.add(item);
//					}
//					items.clear();
//					items.addAll(list);
//				} catch (Exception e) {
//
//				}
//				handler.sendEmptyMessage(1);
				
			}
		}.start();
		AsyncHttpClient client = new AsyncHttpClient();
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
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				adapter.setItems(items);
				viewFlipper.setAdapter(adapter);

				viewFlipper.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						int size = mSlideViews.size();
						int j = arg2 % size;
						for (int i = 0; i < size; i++) {
							ImageView imageView = mSlideViews.get(i);
							if (j == i)
								imageView.setBackgroundResource(R.drawable.item_gallery_select);
							else
								imageView.setBackgroundResource(R.drawable.item_gallery_none);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				viewFlipper.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					}
				});
				break;
			case 100:
				if(viewFlipper!=null){
					if(viewFlipper.getSelectedItemPosition()==viewFlipper.getCount()-1){
						viewFlipper.setSelection(0);
					}else if(viewFlipper.getSelectedItemPosition()==viewFlipper.getCount()-2){
						viewFlipper.setSelection(4);
					}else{
//						viewFlipper.onScroll(null, null, -1*(20+20), 0);
						viewFlipper.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
					}					
				}
//					viewFlipper.setSelection(viewFlipper.getSelectedItemPosition() + 1);
				
				break;
			}
		}
	};
}
