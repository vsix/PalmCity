package com.palmcity.adapter;


import java.util.ArrayList;
import java.util.List;

import com.palmcity.R;
import com.palmcity.util.ImageLoaderUtil;
import com.palmcity.vo.MeiList2Vo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MeiList2Adapter extends BaseAdapter {

	private List<MeiList2Vo> items;
	private LayoutInflater layoutInflater;
	private float ratio;
	ImageLoaderUtil loader;

	public MeiList2Adapter(Context context,float ratio) {
		this.items = new ArrayList<MeiList2Vo>();
		loader = new ImageLoaderUtil(context);
		this.layoutInflater = LayoutInflater.from(context);
		this.ratio = ratio;
	}

	public void setItems(List<MeiList2Vo> items) {
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
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = this.layoutInflater.inflate(R.layout.item_mei_list2, null);
			holder.title = (TextView) convertView
					.findViewById(R.id.newslistitem_title);
			holder.author = (TextView) convertView
					.findViewById(R.id.newslistitem_author);
			holder.time = (TextView) convertView
					.findViewById(R.id.newslistitem_time);
			holder.imgsingle = (ImageView) convertView
					.findViewById(R.id.newslistitem_imgsingle);
			holder.imglayout = (LinearLayout) convertView
					.findViewById(R.id.newslistitem_imglayout);
			holder.img1 = (ImageView) convertView
					.findViewById(R.id.newslistitem_img1);
			holder.img2 = (ImageView) convertView
					.findViewById(R.id.newslistitem_img2);
			holder.img3 = (ImageView) convertView
					.findViewById(R.id.newslistitem_img3);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		MeiList2Vo item = items.get(position);
		holder.title.setMaxWidth(600);
		if (!item.getImg1().equals("") && item.getImg2().equals("")
				&& item.getImg3().equals("")) {
			holder.imgsingle.setVisibility(View.VISIBLE);
			holder.title.setMaxWidth((int) (250*ratio));
			loader.displayImage(item.getImg1(), holder.imgsingle);
		} else {
			holder.imgsingle.setVisibility(View.GONE);
		}
		
		holder.title.setText(item.getTitle());
		holder.author.setText(item.getAuthor());
		holder.time.setText(item.getTime());

		if (!item.getImg1().equals("") && !item.getImg2().equals("")
				&& !item.getImg3().equals("")) {
			holder.imglayout.setVisibility(View.VISIBLE);
			loader.displayImage(item.getImg1(), holder.img1);
			loader.displayImage(item.getImg2(), holder.img2);
			loader.displayImage(item.getImg3(), holder.img3);
		} else {
			holder.imglayout.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ViewHolder {
		public TextView title;
		public TextView author;
		public TextView time;
		public ImageView imgsingle;
		public ImageView img1;
		public ImageView img2;
		public ImageView img3;
		public LinearLayout imglayout;
	}

}
