package com.palmcity.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

public class Bimp {
	public static int max = 0;
	public static boolean act_bool = true;
	public static List<Bitmap> bmp = new ArrayList<Bitmap>();	
	
	//图片sd地址  上传服务器时把图片调用下面方法压缩后 保存到临时文件夹 图片压缩后小于100KB，失真度不明显
	public static List<String> drr = new ArrayList<String>();
	

	public static Bitmap revitionImageSize(String path) throws IOException {
		int degree = readPictureDegree(path);
		
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				new File(path)));
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(in, null, options);
		in.close();
		int i = 0;
		Bitmap bitmap = null;
		while (true) {
			if ((options.outWidth >> i <= 1000)
					&& (options.outHeight >> i <= 1000)) {
				in = new BufferedInputStream(
						new FileInputStream(new File(path)));
				options.inSampleSize = (int) Math.pow(2.0D, i);
				options.inJustDecodeBounds = false;
				bitmap = BitmapFactory.decodeStream(in, null, options);
				break;
			}
			i += 1;
		}
		bitmap = rotaingImageView(degree, bitmap);
		return bitmap;
	}
	
	public static Bitmap rotaingImageView(int angle , Bitmap bitmap) {  
        //旋转图片 动作  
        Matrix matrix = new Matrix();;  
        matrix.postRotate(angle);  
        System.out.println("angle2=" + angle);  
        // 创建新的图片  
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,  
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);  
        return resizedBitmap;  
    }
	
	public static int readPictureDegree(String path) {  
	       int degree  = 0;  
	       try {  
	               ExifInterface exifInterface = new ExifInterface(path);  
	               int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
	               switch (orientation) {  
	               case ExifInterface.ORIENTATION_ROTATE_90:  
	                       degree = 90;  
	                       break;  
	               case ExifInterface.ORIENTATION_ROTATE_180:  
	                       degree = 180;  
	                       break;  
	               case ExifInterface.ORIENTATION_ROTATE_270:  
	                       degree = 270;  
	                       break;  
	               }  
	       } catch (IOException e) {  
	               e.printStackTrace();  
	       }  
	       return degree;  
	   } 
}
