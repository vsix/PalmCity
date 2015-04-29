package com.palmcity.util;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.palmcity.R;

public class ImageLoaderUtil {

	
	DisplayImageOptions options,options2,options3,options4,options5;
	ImageLoader imageLoader;
	
	public ImageLoaderUtil(Context context){
		File cacheDir = StorageUtils.getCacheDirectory(context);
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
        .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
        .threadPoolSize(3) // default
        .threadPriority(Thread.NORM_PRIORITY - 1) // default
        .tasksProcessingOrder(QueueProcessingType.FIFO) // default
        .denyCacheImageMultipleSizesInMemory()
        .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
        .memoryCacheSize(2 * 1024 * 1024)
        .discCache(new UnlimitedDiscCache(cacheDir)) // default
        .discCacheSize(50 * 1024 * 1024)
        .discCacheFileCount(100)
        .build();
		
		
		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
		
		options = new DisplayImageOptions.Builder()
		.showImageOnLoading(R.drawable.ic_loading)
		.showImageForEmptyUri(R.drawable.ic_loading)
		.showImageOnFail(R.drawable.ic_loading)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.considerExifParams(true)
		.bitmapConfig(Bitmap.Config.RGB_565)
		.build();
		
//		options2 = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.ic_loading)
//		.showImageForEmptyUri(R.drawable.ic_loading)
//		.showImageOnFail(R.drawable.ic_loading)
//		.cacheInMemory(true)
//		.cacheOnDisc(true)
//		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(10))
//		.bitmapConfig(Bitmap.Config.RGB_565)
//		.build();
//		
//
//		options4 = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.cc_sr_user_pic)
//		.showImageForEmptyUri(R.drawable.cc_sr_user_pic)
//		.showImageOnFail(R.drawable.cc_sr_user_pic)
//		.cacheInMemory(true)
//		.cacheOnDisc(true)
//		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(200))
//		.bitmapConfig(Bitmap.Config.RGB_565)
//		.build();
//		
//		options5 = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.cc_sr_interest_more)
//		.showImageForEmptyUri(R.drawable.cc_sr_interest_more)
//		.showImageOnFail(R.drawable.cc_sr_interest_more)
//		.cacheInMemory(true)
//		.cacheOnDisc(true)
//		.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
//		.considerExifParams(true)
//		.displayer(new RoundedBitmapDisplayer(200))
//		.bitmapConfig(Bitmap.Config.RGB_565)
//		.build();
		
		com.nostra13.universalimageloader.utils.L.disableLogging();
	}
	
	public void displayImage(String url,ImageView imageView){
		imageLoader.displayImage(url, imageView,options);
//		imageLoader.displayImage(url, imageView);
	}
	
	public void changeLight(ImageView imageView,int light){
		ColorMatrix matrix = new ColorMatrix();
	    matrix.set(new float[] { 1, 0, 0, 0, light, 0, 1, 0, 0,
	    		light, 0, 0, 1, 0, light, 0, 0, 0, 1, 0 });
	    imageView.setColorFilter(new ColorMatrixColorFilter(matrix));
	}
	
	public void displayRoundedImage(String url,ImageView imageView){
		imageLoader.displayImage(url, imageView,options2);
	}
	
	public void displayCircleUserPic(String url,ImageView imageView){
		imageLoader.displayImage(url, imageView,options4);
	}
	public void displayCircleInterest(String url,ImageView imageView){
		imageLoader.displayImage(url, imageView,options5);
	}
	
	public void displayCircleImage(String url,ImageView imageView){
		if(options3==null){
			options3 = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.ic_loading)
			.showImageForEmptyUri(R.drawable.ic_loading)
			.showImageOnFail(R.drawable.ic_loading)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
			.considerExifParams(true)
			.displayer(new RoundedBitmapDisplayer(200))
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
		}
		imageLoader.displayImage(url, imageView,options3);
	}
	
	public void loadImage(String url,ImageLoadingListener listener){
		imageLoader.loadImage(url, listener);
	}
	
	public Bitmap getBitmap(String url){		
		return imageLoader.loadImageSync(url);
	}
	
	public void loadImage(String url,ImageView imageView,ImageLoadingListener listener){
		imageLoader.displayImage(url,imageView, options, listener);
	}
	
	public void loadImageWithoutIC(String url,ImageView imageView){
		if(options3==null){
			options3 = new DisplayImageOptions.Builder()
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();
		}
		imageLoader.displayImage(url, imageView,options3);
	}
}
