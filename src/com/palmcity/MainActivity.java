package com.palmcity;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
	
	List<Fragment> fragmentList;
	int currIndex;
	Button bt_hui;
	Button bt_qi;
	Button bt_mei;
	ViewPager viewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		bt_hui = (Button) findViewById(R.id.main_bt_hui);
		bt_qi = (Button) findViewById(R.id.main_bt_qi);
		bt_mei = (Button) findViewById(R.id.main_bt_mei);
		
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(new HuiFragment());
//		fragmentList.add(new WoYiGouFragment());
//		fragmentList.add(new WoFangBianFragment());
		viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),
				fragmentList));
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		
		bt_hui.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(0);
			}
		});
		bt_qi.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(1);
			}
		});
		bt_mei.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				viewPager.setCurrentItem(2);
			}
		});
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
//		int one = offset * 2 + bmpW;// 页卡1->页卡2偏移量
//		int two = one * 2;// 页卡1->页卡3偏移量

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
//			Animation animation = new TranslateAnimation(one * currIndex, one
//					* arg0, 0, 0);
			currIndex = arg0;
//			animation.setFillAfter(true);// 图片停在动画结束位置
//			animation.setDuration(300);
//			cursor.setAnimation(animation);
			switch (currIndex) {
			case 0:
				bt_hui.setTextColor(Color.parseColor("#ff4c02"));
				bt_qi.setTextColor(Color.parseColor("#898989"));
				bt_mei.setTextColor(Color.parseColor("#898989"));
				break;
			case 1:
				bt_hui.setTextColor(Color.parseColor("#898989"));
				bt_qi.setTextColor(Color.parseColor("#ff4c02"));
				bt_mei.setTextColor(Color.parseColor("#898989"));
				break;
			case 2:
				bt_hui.setTextColor(Color.parseColor("#898989"));
				bt_qi.setTextColor(Color.parseColor("#898989"));
				bt_mei.setTextColor(Color.parseColor("#ff4c02"));
				break;
			}
		}

	}
	
	class MyPagerAdapter extends FragmentStatePagerAdapter {

		private List<Fragment> fragmentList;
		private FragmentManager fm;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
			super(fm);
			this.fragmentList = fragmentList;
			this.fm = fm;
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			if (fragmentList == null || fragmentList.size() == 0) {
				return null;
			} else {
				return fragmentList.get(arg0);
			}
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return fragmentList == null ? 0 : fragmentList.size();
		}

	}
	

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
