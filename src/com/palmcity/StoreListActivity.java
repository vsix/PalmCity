package com.palmcity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.palmcity.adapter.StoreListAdapter;
import com.palmcity.vo.StoreListVo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class StoreListActivity extends BaseActivity {
	
	private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	StoreListAdapter adapter;
	int page = 1;
	List<StoreListVo> items;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store_list);
		((ImageButton) findViewById(R.id.bt_return))
		.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		mPullRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
		items = new ArrayList<StoreListVo>();
		adapter = new StoreListAdapter(this);
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
		mGridView.setAdapter(adapter);
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
	}
	
	private void loading() {
		client.get("", new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
				// TODO Auto-generated method stub
				Gson gson = new Gson();
				List<StoreListVo> newItem = gson.fromJson("", new TypeToken<List<StoreListVo>>(){}.getType());
				items.addAll(newItem);
				adapter.setItems(items);
				mPullRefreshGridView.onRefreshComplete();
				page++;
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				mPullRefreshGridView.onRefreshComplete();
			}
		});
	}

}
