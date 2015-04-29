package com.palmcity.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.palmcity.R;
import com.palmcity.util.ImageLoaderUtil;
import com.palmcity.vo.HuiAdVo;
import com.palmcity.vo.QiListVo;

public class QiListAdapter extends BaseAdapter {
	private List<QiListVo> items;
	private LayoutInflater layoutInflater;
	private ImageLoaderUtil imageLoader;

	public QiListAdapter(Context context) {
		this.items = new ArrayList<QiListVo>();
		this.layoutInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoaderUtil(context);
	}

	public void setItems(List<QiListVo> items) {
		this.items.clear();
		this.items.addAll(items);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
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
		ViewHolder holder = null;
		if (convertView == null) {

			holder = new ViewHolder();
			convertView = this.layoutInflater.inflate(
					R.layout.item_qi_list, null);
			holder.image = (ImageView) convertView
					.findViewById(R.id.image);
			holder.name = (TextView) convertView
					.findViewById(R.id.name);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		QiListVo item = items.get(position % items.size());
		// imageLoader.displayImage(item.getImg1(), holder.image);
		// holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		// holder.name.setText(item.getTitle());
		// }
		return convertView;
	}

	private class ViewHolder {
		public ImageView image;
		public TextView name;
	}
}
