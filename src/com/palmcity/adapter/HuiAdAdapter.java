package com.palmcity.adapter;

import java.util.ArrayList;
import java.util.List;

import com.palmcity.R;
import com.palmcity.util.ImageLoaderUtil;
import com.palmcity.vo.HuiAdVo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HuiAdAdapter extends BaseAdapter  {

	private List<HuiAdVo> items;
	private LayoutInflater layoutInflater;
	private ImageLoaderUtil imageLoader;

	public HuiAdAdapter(Context context) {
		this.items = new ArrayList<HuiAdVo>();
		this.layoutInflater = LayoutInflater.from(context);
		imageLoader = new ImageLoaderUtil(context);
	}

	public void setItems(List<HuiAdVo> items) {
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
		// TODO Auto-generated method stub
//		if (items.get(position % items.size()).getImg1().equals("")) {
//			convertView = this.layoutInflater.inflate(
//					R.layout.main_news_gallery_item2, null);
//			TextView tx = (TextView) convertView
//					.findViewById(R.id.news_gallery_item2_tx);
//			tx.setText(items.get(position % items.size()).getTitle());
//		} else {
			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();
				convertView = this.layoutInflater.inflate(
						R.layout.item_main_news_gallery, null);
				holder.image = (ImageView) convertView
						.findViewById(R.id.main_news_gallery_item_img);
				holder.name = (TextView) convertView
						.findViewById(R.id.main_news_gallery_item_tx);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			HuiAdVo item = items.get(position % items.size());
			imageLoader.displayImage(item.getImg1(), holder.image);
			holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
			holder.name.setText(item.getTitle());
//		}
		return convertView;
	}

	private class ViewHolder {
		public ImageView image;
		public TextView name;
	}

}
