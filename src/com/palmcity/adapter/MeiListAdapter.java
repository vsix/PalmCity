package com.palmcity.adapter;

import java.util.ArrayList;
import java.util.List;





import com.palmcity.R;
import com.palmcity.vo.MeiListVo;
import com.palmcity.vo.StoreListVo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeiListAdapter extends BaseAdapter {

	private List<MeiListVo> items;
	private LayoutInflater layoutInflater;

	public MeiListAdapter(Context context) {
		this.items = new ArrayList<MeiListVo>();
		this.layoutInflater = LayoutInflater.from(context);
	}

	public void setItems(List<MeiListVo> items) {
		this.items.clear();
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size()+3;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = this.layoutInflater.inflate(R.layout.item_mei_list,
					null);
			holder.tx = (TextView) convertView
					.findViewById(R.id.liveshow_item_tx);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
//		if (position % 2 == 0) {
//			holder.tx.setBackgroundResource(R.drawable.custom_gridview_bg1_bt);
//		} else {
//			holder.tx.setBackgroundResource(R.drawable.custom_gridview_bg2_bt);
//		}
		if(position<items.size()){
			MeiListVo item = items.get(position);
//			holder.tx.setText(item.getName());
			holder.tx.setVisibility(View.VISIBLE);
		}else{
			holder.tx.setText("");
			holder.tx.setVisibility(View.INVISIBLE);
		}
		return convertView;
	}

	private class ViewHolder {
		public TextView tx;
	}

}
