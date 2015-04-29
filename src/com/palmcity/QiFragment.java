package com.palmcity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.palmcity.adapter.HuiAdAdapter;
import com.palmcity.adapter.QiListAdapter;
import com.palmcity.adapter.StoreListAdapter;
import com.palmcity.vo.HuiAdVo;
import com.palmcity.vo.QiListVo;
import com.palmcity.vo.StoreListVo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;

public class QiFragment extends Fragment{
	
	private PullToRefreshGridView mPullRefreshGridView;
	private GridView mGridView;
	QiListAdapter adapter;
	int page = 1;
	List<QiListVo> items;
	
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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		items = new ArrayList<QiListVo>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View v = inflater.inflate(R.layout.fragment_qi, container,
				false);
		mPullRefreshGridView = (PullToRefreshGridView) v.findViewById(R.id.pull_refresh_grid);
		adapter = new QiListAdapter(getActivity());
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
		loading();
		return v;
	}
	
	private void loading(){
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
}
