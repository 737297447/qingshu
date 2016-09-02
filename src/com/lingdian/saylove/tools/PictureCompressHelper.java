package com.lingdian.saylove.tools;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

/**
 * 图片处理，压缩图片工具类
 * @author shenxy
 * 
 */
public class PictureCompressHelper {
	// 图片质量
	public int w = 1280;
	public int h = 720;

	public PictureCompressHelper() {
	}

	public PictureCompressHelper(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public void compressPic(File inputfile) {

//		ByteArrayOutputStream baos = null ;
		FileOutputStream out = null;
		Bitmap bm = null;
		try{
	    	String filePath = inputfile.getAbsolutePath();
	    	
			BitmapFactory.Options options = new BitmapFactory.Options();
			// 只获取图片的宽和高，不获取图片元素，减少内存占用
			options.inJustDecodeBounds = true;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			BitmapFactory.decodeFile(filePath, options);
	
			// 计算图片压缩比率
			int num = calculateInSampleSize(options, w, h);
			Log.d("nzl","压缩图片比率："+num);
			options.inSampleSize = num;
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			bm = BitmapFactory.decodeFile(filePath, options);
			if(bm == null){
				return ;
			}
			// 判断图片角度，是否需要进行旋转图片操作
			int degree = readPictureDegree(filePath);
			bm = rotateBitmap(bm,degree) ;
			out =  new FileOutputStream(inputfile);
			
			bm.compress(Bitmap.CompressFormat.JPEG, 70, out);
			
		}catch (FileNotFoundException e) {
			Log.d("nzl","转换图片错误compressPic》找不到文件");
		}finally{
			try {
				// 关闭流
				if(out != null){
					out.flush();
					out.close() ;
				}
				// 释放BitMap
				if(bm!=null && !bm.isRecycled()){
					bm.recycle();
					bm = null ;
				}
			} catch (IOException e) {
				Log.d("nzl","转换图片错误compressPic》"+e.getMessage());
			}
		}
	}
	

	public void saveBitMapToFileHigh(Bitmap bm,File outFile){
		if(bm == null){
			return ;
		}
		ByteArrayOutputStream baos = null ;
		FileOutputStream out = null;
		try {
			int degree = readPictureDegree(outFile.getAbsolutePath());
			bm = rotateBitmap(bm, degree);
			out = new FileOutputStream(outFile);
			bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
		} catch (OutOfMemoryError e) {
			Log.d("nzl","转换图片错误compressPic》内存不足");
		} catch (FileNotFoundException e) {
			Log.d("nzl","转换图片错误compressPic》找不到文件");
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (bm != null && !bm.isRecycled()) {
					bm.recycle();
					bm = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void saveBitMapToFile(Bitmap bm,File outFile){
		if(bm == null){
			return ;
		}
		ByteArrayOutputStream baos = null ;
		FileOutputStream out = null;
		try {
			int degree = readPictureDegree(outFile.getAbsolutePath());
			bm = rotateBitmap(bm, degree);
			out = new FileOutputStream(outFile);
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
		} catch (OutOfMemoryError e) {
			Log.d("nzl","转换图片错误compressPic》内存不足");
		} catch (FileNotFoundException e) {
			Log.d("nzl","转换图片错误compressPic》找不到文件");
		} finally {
			try {
				if (baos != null) {
					baos.close();
				}
				if (bm != null && !bm.isRecycled()) {
					bm.recycle();
					bm = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void compressPic(File inputfile,File outputfile) {
//		ByteArrayOutputStream baos = null ;
		FileOutputStream out = null;
		Bitmap bm=null;
		try{
			if(!outputfile.getParentFile().exists()){
				outputfile.getParentFile().mkdirs();
			}
	    	String filePath = inputfile.getAbsolutePath();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			BitmapFactory.decodeFile(inputfile.getAbsolutePath(), options);

			int num = calculateInSampleSize(options, w, h);
			Log.d("nzl","压缩图片比率："+num);
			options.inSampleSize = num;
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			
			bm = BitmapFactory.decodeFile(filePath, options);
			if(bm == null){
				return ;
			}
			int degree = readPictureDegree(filePath);
			bm = rotateBitmap(bm,degree) ;
			out =  new FileOutputStream(outputfile);
			bm.compress(Bitmap.CompressFormat.JPEG, 75, out);
		}catch (FileNotFoundException e) {
			Log.d("nzl","转换图片错误compressPic》找不到文件");
		}finally{
			try {
				// 关闭流
				if(out != null){
					out.flush();
					out.close() ;
				}
				// 释放BitMap
				if(bm!=null && !bm.isRecycled()){
					bm.recycle();
					bm = null ;
				}
			} catch (IOException e) {
				Log.d("nzl","转换图片错误>compressPic(1,2)》"+e.getMessage());
			}
		}
	}

	public Bitmap compressPic(String filePath) {
		ByteArrayOutputStream baos = null ;
		try{
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			BitmapFactory.decodeFile(filePath, options);
	
			// Calculate inSampleSize
			int size = calculateInSampleSize(options, h, w);
			// 图片压缩比率
			options.inSampleSize = size;
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;
			
			Bitmap bm = BitmapFactory.decodeFile(filePath, options);
			if(bm == null){
				return null;
			}
			int degree = readPictureDegree(filePath);
			bm = rotateBitmap(bm,degree) ;
			baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 75, baos);
			return bm;
		}catch (OutOfMemoryError e) {
			Log.d("nzl","转换图片错误compressPic》内存不足");
			return null ;
		}catch (Exception e) {
			return null ;
		}finally{
			try {
				if(baos != null){
					baos.flush();
					baos.close() ;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Bitmap rotateBitmap(Bitmap bitmap, int rotate){
		try{
			if(bitmap == null)
				return null ;
			
			int w = bitmap.getWidth();
			int h = bitmap.getHeight();
	
			// Setting post rotate to 90
			Matrix mtx = new Matrix();
			mtx.postRotate(rotate);
			return Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, true);
		}catch(Exception e){
			return bitmap;
		}
	}
	
	/**
	 * 判断图片角度，是否需要旋转
	 * @param path
	 * @return
	 */
	private static int readPictureDegree(String path) {  
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
	
	/**
	 * 计算图片压缩比率
	 * @param options
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
		}

		return inSampleSize;
	}
	
	public Bitmap readBitMap(Context context,int resId,int size) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = size;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
	}
	
	public Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 10, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 10;
        while ( baos.toByteArray().length / 1024>100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩        
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


	
}
