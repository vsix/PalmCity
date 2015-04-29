package com.palmcity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.palmcity.adapter.MeiList2Adapter;
import com.palmcity.adapter.StoreListAdapter;
import com.palmcity.vo.MeiList2Vo;
import com.palmcity.vo.StoreListVo;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class MeiListActivity extends BaseActivity {
	
	private PullToRefreshListView mPullRefreshListView;
	private ListView mListView;
	MeiList2Adapter adapter;
	int page = 1;
	List<MeiList2Vo> items;
	float ratio_width;
	float ratio_height;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mei_list);
		initRatio();
		((ImageButton) findViewById(R.id.bt_return))
		.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);
		items = new ArrayList<MeiList2Vo>();
		adapter = new MeiList2Adapter(this,ratio_width);
		mListView = mPullRefreshListView.getRefreshableView();
		mPullRefreshListView.setMode(Mode.BOTH);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				items.clear();
				page = 1;
				loading();
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO Auto-generated method stub
				loading();
			}

			/*@Override
			public void onRefresh(PullToRefreshBase<GridView> refreshView) {
				// TODO Auto-generated method stub
				new GetDataTask().execute();
			}*/

		});
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				ServerNewsList news = (ServerNewsList) adapter.getItem(arg2);
//				Intent intent = new Intent(NewsActivity.this,
//						NewsDetailActivity.class);
//				Bundle extras = new Bundle();
//				extras.putString("newsId", news.getId());
//				intent.putExtras(extras);
//				startActivity(intent);overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
			}
		});
	}
	
	private void loading() {
		client.get("", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				List<MeiList2Vo> newItem = gson.fromJson("", new TypeToken<List<MeiList2Vo>>(){}.getType());
				items.addAll(newItem);
				adapter.setItems(items);
				mPullRefreshListView.onRefreshComplete();
				page++;
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				mPullRefreshListView.onRefreshComplete();
			}
		});
	}
	
	private void initRatio(){
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float SCREEN_HEIGHT;
		float SCREEN_WIDHT;
		int tempHeight = (int) (SCREEN_HEIGHT = dm.heightPixels);
		int tempWidth = (int) (SCREEN_WIDHT = dm.widthPixels);

		if (tempHeight > tempWidth) {
			SCREEN_HEIGHT = tempHeight;
			SCREEN_WIDHT = tempWidth;
		} else {
			SCREEN_HEIGHT = tempWidth;
			SCREEN_WIDHT = tempHeight;
		}
		float zoomx = SCREEN_WIDHT / 480;
		float zoomy = SCREEN_HEIGHT / 800;
		if (zoomx > zoomy) {
			ratio_width = ratio_height = zoomy;
		} else {
			ratio_width = ratio_height = zoomx;
		}
		Log.i("", "ratio_width:"+ratio_width);
	}

}
